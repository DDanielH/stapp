package de.whs.stapp.data.bluetooth;

import java.lang.reflect.Method;
import java.util.Set;

import zephyr.android.HxMBT.BTClient;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
 * Diese Klasse dient zum Verbindungsaufbau und zur Kommunikation mit dem
 * HxM-Sensor von Zephyr.
 * 
 * @author Dennis Miller
 * */
public class BluetoothConnection {	
	private static final String HXM_DEVICE_NAME_PREFIX = "HXM";
	
	private BluetoothAdapter mAdapter;
	private BTClient mClient;
	private HxMConnectedListener mConnListener = new HxMConnectedListener();
	private String mDeviceName;
	private Context mContext;
	
	private BTPairingReceiver mPairingReceiver = new BTPairingReceiver();
	private BTBondReceiver mBondReceiver = new BTBondReceiver();
	private BTStateChangedReceiver mStateChangedReceiver = new BTStateChangedReceiver();
	private BTDeviceFoundReceiver mDeviceFoundReceiver = new BTDeviceFoundReceiver();
	private BTDiscoveryFinishedReceiver mDiscoveryFinishedReceiver = new BTDiscoveryFinishedReceiver();
	
	/**
	 * Erstellt eine Instanz dieser Klasse.
	 * Es wird noch keine Verbindung aufgebaut.
	 * 
	 * @param context Anwendungskontext, in dem die Verbindung aufgebaut werden soll.
	 */
	public BluetoothConnection(Context context) {
		this.mContext = context;
	}
	
	/**
	 * Registriert Broadcast-Receiver f�r bestimmte Ereignisse, die den 
	 * Bluetooth-Adapter betreffen.
	 */
	public void registerBroadcastReceivers() {

		IntentFilter pairingRequestFilter = new IntentFilter(
				"android.bluetooth.device.action.PAIRING_REQUEST");
		mContext.registerReceiver(mPairingReceiver, pairingRequestFilter);

		IntentFilter bondFilter = new IntentFilter(
				"android.bluetooth.device.action.BOND_STATE_CHANGED");
		mContext.registerReceiver(mBondReceiver, bondFilter);
		
		IntentFilter btStateChangedFilter = new IntentFilter(
				"android.bluetooth.adapter.action.STATE_CHANGED");
		mContext.registerReceiver(mStateChangedReceiver,	btStateChangedFilter);
		
		IntentFilter btDeviceFoundFilter = new IntentFilter(
				"android.bluetooth.device.action.FOUND");
		mContext.registerReceiver(mDeviceFoundReceiver, btDeviceFoundFilter);
		
		IntentFilter btDiscoveryFinishedFilter = new IntentFilter(
				"android.bluetooth.adapter.action.DISCOVERY_FINISHED");
		mContext.registerReceiver(mDiscoveryFinishedReceiver, btDiscoveryFinishedFilter);

	}

	/**
	 * Pr�fe, ob ein Bluetoothger�t verbunden ist.
	 */
	public boolean isOpen() {
		if (mClient == null)
			return false;
		else
			return mClient.IsConnected();
	}

	/**
	 * Diese Methode versucht einen Verbindung zum Bluetoothger�t aufzubauen.
	 * 
	 * @throws BluetoothException 
	 */
	public void open() throws BluetoothException {
		if (isOpen())
			return;

		mAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mAdapter == null)
			throw new NoBluetoothAdapterException();		
		if (!mAdapter.isEnabled())
			throw new BluetoothAdapterDisabledException();
		
		String deviceAddress = findDeviceAddress();
		if (deviceAddress != null)
			connectDevice(deviceAddress);
		
		if (!mAdapter.startDiscovery())
			throw new BluetoothConnectionFailedException();
	}
	
	/**
	 * Diese Methode baut die Verbindung zum Bluetoothger�t ab.
	 */
	public void close() {
		if (mClient != null && mConnListener != null) {
			mClient.removeConnectedEventListener(mConnListener);
			
			mContext.unregisterReceiver(mBondReceiver);
			mContext.unregisterReceiver(mPairingReceiver);
			mContext.unregisterReceiver(mStateChangedReceiver);
			mContext.unregisterReceiver(mDiscoveryFinishedReceiver);
			mContext.unregisterReceiver(mDeviceFoundReceiver);
			
			mClient.Close();
		}
	}
	
	/**
	 * 
	 * Diese Methode gibt lediglich den Ger�tenamen wieder.
	 * 
	 * @return deviceName - Ger�tename
	 */
	public String getDeviceName() {
		return mDeviceName;
	}
	
	/**
	 * Gibt ein DataTracker Objekt zur�ck, �ber das die Datenpakete
	 * vom Sensor empfangen werden k�nnen.
	 * 
	 * @return DataTracker f�r den Sensor.
	 */
	public DataTracker getDataTracker() {
		return mConnListener;
	}

	private String findDeviceAddress() throws BluetoothException {
		Set<BluetoothDevice> pairedDevices = mAdapter.getBondedDevices();
		for (BluetoothDevice device : pairedDevices) {
			if (device.getName().startsWith(HXM_DEVICE_NAME_PREFIX)) {
				return device.getAddress();
			}
		}
		
		return null;
	}
	
	private void connectDevice(String address) throws BluetoothException{
		BluetoothDevice device = mAdapter.getRemoteDevice(address);
		mDeviceName = device.getName();
		mClient = new BTClient(mAdapter, address);
		mClient.addConnectedEventListener(mConnListener);

		if (mClient.IsConnected())
			mClient.start();
		else
			throw new BluetoothConnectionFailedException();
	}
	
	/**
	 * 
	 * Dieser Broadcast-Receiver ist ausgelegt, um auf die �nderung
	 * des Paarung-Status zu reagieren.
	 * 
	 * @author Dennis Miller
	 */
	private class BTBondReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle b = intent.getExtras();
			BluetoothDevice device = mAdapter.getRemoteDevice(b.get(
					"android.bluetooth.device.extra.DEVICE").toString());
			Log.d("Bluetooth", "BOND_STATED = " + device.getBondState());
			// TODO
		}
	}

	/**
	 * 
	 * Dieser Broadcast-Receiver ist ausgelegt, um auf
	 * eine Paarungs-Anfrage zu reagieren.
	 * Diese Anfrage beantwortet er direkt mit dem HxM-Device
	 * Pin "1234".
	 * 
	 * @author Dennis Miller
	 */
	private class BTPairingReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("Bluetooth", "Pairing intent: " + intent.getAction());
			
			Bundle b = intent.getExtras();
			String pairingDevice = b.get("android.bluetooth.device.extra.DEVICE").toString();
			String pairingVariant = b.get("android.bluetooth.device.extra.PAIRING_VARIANT").toString();
			Log.d("Bluetooth", "Pairing device: " + pairingDevice);
			Log.d("Bluetooth", "Pairing variant: " + pairingVariant);
			
			try {
				BluetoothDevice device = mAdapter.getRemoteDevice(pairingDevice);
				Method convertPinToBytes = device.getClass()
						.getMethod("convertPinToBytes",	new Class[] { String.class });
				byte[] pin = (byte[]) convertPinToBytes.invoke(device, "1234");
				
				Method setPin = device.getClass().getMethod("setPin", new Class[] { pin.getClass() });
				Object result = setPin.invoke(device, pin);
				
				Log.d("Bluetooth", "Setting PIN to '1234' (" + result.toString() + ")");
			} catch (Exception e) {
				Log.e("Bluetooth", "Error while pairing devices: " + e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * Dieser Broadcast-Receiver wird genutzt um einen Status-Wechsel
	 * des Bluetooth-Adapters zu registrieren.
	 * 
	 * @author Dennis Miller
	 */
	private class BTStateChangedReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
					
				Bundle b = intent.getExtras();
				int state = b.getInt("android.bluetooth.adapter.extra.STATE");
				int prevState = b.getInt("android.bluetooth.adapter.extra.PREVIOUS_STATE");
				btAdapterStateChanged(state, prevState);
						
		}
		
		private void btAdapterStateChanged(int state, int previousState){
			switch (state) {
			case BluetoothAdapter.STATE_TURNING_ON:
				//TODO
				break;
			case BluetoothAdapter.STATE_ON:
			
				break;
			case BluetoothAdapter.STATE_TURNING_OFF:
				
				break;
			case BluetoothAdapter.STATE_OFF:
				
				break;					
			}
		}
		
	}
	
	/**
	 * 
	 * Dieser Broadcast-Receiver wird genutzt um gefundene Bluetooth-Devices
	 * zu pr�fen und gegebenenfalls eine Verbindung aufzubauen.
	 * 
	 * @author Dennis Miller
	 */
	private class BTDeviceFoundReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device.getName().equals(mDeviceName)){
            	mAdapter.cancelDiscovery();
            	try {
            		connectDevice(device.getAddress());
            	} catch (BluetoothException e) {
            		Log.e("Bluetooth", "Error while connecting to device: " + e.getMessage());
            	}
            }
		}
	}
	
	/**
	 * Dieser Broadcast-Receiver wird genutzt um bei Beendigung des
	 * Discovery-Vorgangs die Bluetooth-Verbindung zu pr�fen.
	 * 
	 * @author Dennis Miller
	 */
	private class BTDiscoveryFinishedReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (mClient != null || mClient.IsConnected())
				Log.d("Bluetooth", "Client connected after discovery");
			else
				Log.e("Bluetooth", "Client not connected after discovery");
			
		}
		
	}
}

package de.whs.stapp.data.bluetooth;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Diese Klasse ist von N�ten um den BTService zu binden.
 **/
class BTServiceConnection implements ServiceConnection {
	
	private BTCommunicationService btService;
	private ConnectionState connectionState = ConnectionState.Disconnected; 
	
	
	/**
	 * Diese Methode wird aufgerufen, wenn der Service gebunden wurde.
	 * 
	 * @param className - Klassenname
	 * @param service - Service Binder
	 */
	public void onServiceConnected(ComponentName className, IBinder service) {
		BTServiceBinder serviceBinder = (BTServiceBinder) service; 
		btService = serviceBinder.getService();
		try {
			connect();
		} catch (BluetoothException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 /**
	  * Diese Methode wird aufgerufen, wenn der Verbund mit 
	  * dem Service aufgel�st wurde.
	  * 
	  * @param arg0 - Komponentenname
	  */
	public void onServiceDisconnected(ComponentName arg0) {
	}
	
	/**
	 * Diese Methode versucht eine Bluetoothverbindung herzustellen
	 * und meldet den anschlie�enden BT-Status.
	 * @throws BluetoothException 
	 */
	public void connect() throws BluetoothException {
		
		connectionState = ConnectionState.Connecting;
		connectionState = btService.initiateBtConnection();
		
	}
	
	/**
	 * Diese Methode registriert einen Listener,  der die Sensor-Daten erhalten m�chte.
	 * @param listener - Ein Listener, der die Datenpakete des Sensors empfangen m�chte.
	 */
	public void registerListener(TrackedDataListener listener){
		if (btService != null) 
			btService.registerHxMListener(listener);
	}
	

	/**
	 * Gibt den Verbindungsstatus zur�ck.
	 * @return connectionState - Verbindungsstatus
	 */
	public ConnectionState getConnectionState() {
		return connectionState;
	}

	/**
	 * Deregistrierung listener.
	 * @param listener .
	 */
	public void unregisterListener(TrackedDataListener listener) {
		btService.unregisterListener(listener);		
	}
	
	
}
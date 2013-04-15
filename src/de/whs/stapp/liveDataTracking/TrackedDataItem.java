package de.whs.stapp.liveDataTracking;

/**
 * Diese Klasse dient als Datencontainer f�r 
 * die vom nsor gelieferten Daten.
 * 
 * @author Dennis Miller
 *
 */
public class TrackedDataItem {

	private String firmwareId;
	private String firmwareVersion;
	private String hardwareId;
	private String hardwareVersion;
	private byte batteryChargeInd;
	private byte heartRate;
	private byte heartBeatNumber;
	private int[] heartBeatTimestamps;
	private double distance;
	private double speed;
	private byte strides;
	
	
	/**
	 * Parameterloser Konstruktor.
	 */	
	public TrackedDataItem() {
	}

	/**
	 * Konstruktor, welcher sofort alle Variablen bef�llt.
	 *  
	 * @param firmwareId -
	 * @param firmwareVersion -
	 * @param hardwareId -
	 * @param hardwareVersion -
	 * @param batteryChargeInd -
	 * @param heartRate -
	 * @param heartBeatNumber -
	 * @param heartBeatTimestamps -
	 * @param distance - 
	 * @param speed -
	 * @param strides -
	 */
	public TrackedDataItem(String firmwareId, String firmwareVersion,
			String hardwareId, String hardwareVersion, byte batteryChargeInd,
			byte heartRate, byte heartBeatNumber, int[] heartBeatTimestamps,
			double distance, double speed, byte strides) {
		this.firmwareId = firmwareId;
		this.firmwareVersion = firmwareVersion;
		this.hardwareId = hardwareId;
		this.hardwareVersion = hardwareVersion;
		this.batteryChargeInd = batteryChargeInd;
		this.heartRate = heartRate;
		this.heartBeatNumber = heartBeatNumber;
		this.heartBeatTimestamps = heartBeatTimestamps;
		this.distance = distance;
		this.speed = speed;
		this.strides = strides;
	}

	/**
	 * 
	 * Getter-Methode f�r firmwareId.
	 * 
	 * @return firmwareId - FirmwareID
	 */
	public String getFirmwareId() {
		return firmwareId;
	}

	/**
	 * 
	 * Setter-Methode f�r firmwareId.
	 * 
	 * @param firmwareId
	 *            - Firmware ID
	 */
	public void setFirmwareId(String firmwareId) {
		this.firmwareId = firmwareId;
	}

	/**
	 * 
	 * Getter-Methode f�r.
	 * 
	 * @return firmwareVersion - Firmware Version
	 */
	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	/**
	 * 
	 * Setter-Methode f�r firmwareVersion.
	 * 
	 * @param firmwareVersion
	 *            - Firmware Version
	 */
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * 
	 * Getter-Methode f�r HardwareId.
	 * 
	 * @return hardwareId - Hardware ID
	 */
	public String getHardwareId() {
		return hardwareId;
	}

	/**
	 * 
	 * Setter-Methode f�r hardwareId.
	 * 
	 * @param hardwareId
	 *            - Hardware ID
	 */
	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}

	/**
	 * 
	 * Getter-Methode f�r hardwareVersion.
	 * 
	 * @return hardwareVersion - Hardware Version
	 */
	public String getHardwareVersion() {
		return hardwareVersion;
	}

	/**
	 * 
	 * Setter-Methode f�r hardwareVersion.
	 * 
	 * @param hardwareVersion
	 *            - Hardware Version
	 */
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	/**
	 * 
	 * Getter-Methode f�r batteryChargeInd.
	 * 
	 * @return batteryChargeInd - Batterie Status
	 */
	public byte getBatteryChargeInd() {
		return batteryChargeInd;
	}

	/**
	 * 
	 * Setter-Methode f�r batteryChargeInd.
	 * 
	 * @param batteryChargeInd
	 *            - Batterie Status
	 */
	public void setBatteryChargeInd(byte batteryChargeInd) {
		this.batteryChargeInd = batteryChargeInd;
	}

	/**
	 * 
	 * Getter-Methode f�r heartRate.
	 * 
	 * @return heartRate - Herzfrequenz
	 */
	public byte getHeartRate() {
		return heartRate;
	}

	/**
	 * 
	 * Setter-Methode f�r heartRate.
	 * 
	 * @param heartRate
	 *            - Herzfrequenz
	 */
	public void setHeartRate(byte heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * 
	 * Getter-Methode f�r heartBeatNumber.
	 * 
	 * @return heartBeatNumber - Herzschlag-Nummer
	 */
	public byte getHeartBeatNumber() {
		return heartBeatNumber;
	}

	/**
	 * 
	 * Setter-Methode f�r heartBeatNumber.
	 * 
	 * @param heartBeatNumber
	 *            - Herzschlag-Nummer
	 */
	public void setHeartBeatNumber(byte heartBeatNumber) {
		this.heartBeatNumber = heartBeatNumber;
	}

	/**
	 * 
	 * Getter-Methode f�r heartBeatTimestamps.
	 * 
	 * @return heartBeatTimestamps[] - Zeitstempel von Herzschl�gen
	 */
	public int[] getHeartBeatTimestamps() {
		return heartBeatTimestamps;
	}

	/**
	 * 
	 * Setter-Methode f�r heartBeatTimestamps.
	 * 
	 * @param heartBeatTimestamps
	 *            - Zeitstempel von Herzschl�gen
	 */
	public void setHeartBeatTimestamps(int[] heartBeatTimestamps) {
		this.heartBeatTimestamps = heartBeatTimestamps;
	}

	/**
	 * 
	 * Getter-Methode f�r distance.
	 * 
	 * @return distance- Distanz
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * 
	 * Setter-Methode f�r distance.
	 * 
	 * @param distance
	 *            - Distanz
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * 
	 * Getter-Methode f�r speed.
	 * 
	 * @return speed - Geschwindigkeit
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * 
	 * Setter-Methode f�r speed.
	 * 
	 * @param speed
	 *            - Geschwindigkeit
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * 
	 * Getter-Methode f�r strides.
	 * 
	 * @return strides - Schritte
	 */
	public byte getStrides() {
		return strides;
	}

	/**
	 * 
	 * Setter-Methode f�r strides.
	 * 
	 * @param strides
	 *            - Schritte
	 */
	public void setStrides(byte strides) {
		this.strides = strides;
	}
	
}

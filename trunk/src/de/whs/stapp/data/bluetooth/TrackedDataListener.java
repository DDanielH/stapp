package de.whs.stapp.data.bluetooth;

import java.util.EventListener;

/**
 * 
 * Dieses Interface dient zur �betragung des HxM-Sensordaten 
 * an die Listener.
 * 
 * @author Dennis Miller
 */
public interface TrackedDataListener extends EventListener {

	/**
	 * 
	 * Diese Methode bekommt mittels des Events die Daten 
	 * des HxM-Sensors �bermittelt.
	 * 
	 * @param dataItem - Das {@link TrackedDataItem}.
	 */
	void trackData(TrackedDataItem dataItem);
	
}

package de.whs.stapp.liveDataTracking;

import java.util.EventListener;

/**
 * 
 * Dieses Interface dient zur �betragung des HxM-Sensordaten 
 * an die Listener mittels eines Events.
 * 
 * @author Dennis Miller
 */
public interface TrackedDataListener extends EventListener {

	/**
	 * 
	 * Diese Methode bekommt mittels des Events die Daten 
	 * des HxM-Sensors �bermittelt.
	 * 
	 * @param e - BTCommunicationEvent
	 */
	void getTrackedData(TrackedDataEvent e);
	
}

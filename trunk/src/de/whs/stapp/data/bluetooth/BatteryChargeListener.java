package de.whs.stapp.data.bluetooth;

/**
 * Listener-Schnittstelle, um die Ver�nderung des Batterie-Ladezustandes
 * zu verfolgen.
 * 
 * @author Fabian
 *
 */
public interface BatteryChargeListener {
	/**
	 * Wird aufgerufen, wenn sich der Ladezustand �ndert.
	 * 
	 * @param charge Aktueller Ladezustand.
	 */
	void onChange(int charge);
}

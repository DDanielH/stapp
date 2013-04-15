package de.whs.stapp.liveDataTracking;

/**
 * .
 * @author Dennis
 */
public interface BTServiceConnectionRegisterable {
	/**
	 * Diese Methode muss implementiert werden um 
	 * Listener registrieren zu k�nnen.
	 * @param listener - Empf�nger der Datenpakete.
	 */
	void registerListener(TrackedDataListener listener);
}

package de.whs.stapp.liveDataTracking;

// TODO, Chris:
// Wurde geschaffen, um eine Mock Implementierung daf�r zu schreiben, 
// damit die damit verbundene Logik auch in Unit-Tests gepr�ft werden
// kann. Fraglich ist nur, ob das nicht einfacher / besser bzw. sch�ner
// geht...?!

/**
 * @author Chris
 * Definiert eine Schnittstelle um sich an TrackedDataEvent
 * registrieren zuk�nnen.
 */
public interface BTServiceConnectionRegisterable {
	
	/**
	 * Diese Methode muss implementiert werden um Listener 
	 * registrieren zu k�nnen.
	 * @param listener Empf�nger der Datenpakete.
	 */
	void registerListener(TrackedDataListener listener);
}

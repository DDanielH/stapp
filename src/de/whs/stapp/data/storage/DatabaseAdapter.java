package de.whs.stapp.data.storage;

import java.util.List;

/**
 * Definiert Methoden f�r den Datenbankzugriff.
 * @author Chris
 */
public interface DatabaseAdapter {
	
	/**
	 * @author Christoph Inhestern
	 * Erforderlich f�r das Verwenden der Schnittstelle.
	 * �ffnet eine Verbindung zur Datenbank.
	 * 
	 */
	void openDatabase();
	
	/**
	 * @author Christoph Inhestern
	 * Schlie�t die mit openDatabase() ge�ffnete Datenbankverbindung.
	 */
	void closeDatabase();
    
	/**
     * @param trainingSessionId Die Id der entsprechenden {@link TrainingSession}.
     * @return Die {@link SessionDetail}s zu einer TrainingSession.
     */
    List<SessionDetail> getSessionDetails(int trainingSessionId);
    
    /**
     * @return Eine Liste aller bestehenden {@link TrainingSession}s.
     */
    List<TrainingSession> getSessionHistory();
        
    /**
     * Speichert ein {@link SessionDetail} zu einer gegebenen TrainingSession.
     * @param detail Das zu speichernde {@link SessionDetail}. 
     */
    void storeSessionDetail(SessionDetail detail);
    
    /**
     * Erstellt eine neue {@link TrainingSession} in der Datenbank.
     * @return Die TrainingSession, die neu in der Datenbank erstellt wurde.
     */
    TrainingSession newTrainingSession();
    
    /**
     * Entfernt die entsprechende Trainings Session vollst�ndig 
     * (inkl. SessionDetails) aus der Datenbank. 
     * @param trainingSessionId Id der zu l�schenden {@link TrainingSession}.
     */
    void deleteTrainingSession(int trainingSessionId);
    
    /**
     * Methode zum Nachtragen der Distanz und der Dauer zu einer TrainingSession
     * Es wird die Relation TrainingSessions abschlie�end erg�nzt.
     * @param session Die TrainingSession die erg�nzt werden soll, enth�lt die entsprechenden Werten.
     */
    void updateTrainingSession(TrainingSession session);
}
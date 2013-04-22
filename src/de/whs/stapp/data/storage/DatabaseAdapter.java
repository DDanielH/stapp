package de.whs.stapp.data.storage;

import java.util.List;

/**
 * Definiert Methoden f�r den Datenbankzugriff.
 * @author Chris
 */
public interface DatabaseAdapter {
	
    /**
     * Gibt alle Daten einer Trainingseinheit zur�ck. Dies enth�lt
     * alle vorhandenen Messwerte zu einer Einheit.
     * 
     * @param trainingUnitId ID der detailliert anzuzeigenden Trainingseinheit.
     * @return
     */
    DetailedTrainingUnit getTrainingUnitDetail(int trainingUnitId);
    
    /**
     * Liefert eine Liste aller Trainingseinheiten zur�ck.
     * @return
     */
    List<TrainingUnit> getTrainingUnitsOverview();
        
    /**
     * Die Methode schreibt Werte zu einer Trainingseinheit in die
     * Datenbank.
     * 
     * @param trainingUnitId Id der aktuell laufenden Trainingseinheit.
     * @param detail Die aktuellen Messwerte, die in der Datenbank gespeichert werden sollen. 
     * @return
     */
    void insertTrainingDetail(int trainingUnitId, TrainingDetail detail);
    
    /**
     * Die Methode legt einen neuen Eintrag f�r eine Trainingseinheit
     * in der Datenbank (in der Tabelle "TrainingsUnit") an.
     * 
     * @return
     */
    TrainingUnit createNewTrainingUnit();
    
    /**
     * Die Methode entfernt alle Eintr�ge zur �bergebenen trainingsUnitId.
     * 
     * @param trainingsUnitId Die ID der zu l�schenden Trainingseinheit
     */
    void removeTrainingUnit(int trainingsUnitId);
    
}

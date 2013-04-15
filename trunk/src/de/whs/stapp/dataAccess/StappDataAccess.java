/**
 * 
 */
package de.whs.stapp.dataAccess;

import java.util.ArrayList;
import java.util.List;

import de.whs.stapp.database.DatabaseAdapter;
import de.whs.stapp.database.TrainingUnit;
import de.whs.stapp.database.TrainingUnitDetail;
import de.whs.stapp.liveDataTracking.BTServiceConnectionRegisterable;

/**
 * @author Chris
 * Implementierung f�r den Zugriff auf die protokollierten Daten.
 */
public class StappDataAccess implements DataAccess {
	
	@Override
	public TrainingController newTraining() {	
		// TODO, Chris: Richtiger Umgang mit Abh�ngigkeiten -> Kl�ren!
		// Ggf. Factory Pattern?!
		DatabaseAdapter databaseAdapter = null;
		BTServiceConnectionRegisterable serviceConnection = null;
		
		return new TrainingController(databaseAdapter, serviceConnection);
	}

	@Override
	public List<TrainingUnit> getTrainingUnitsOverview() {
		// TODO, Chris: Implement this function correctly!
		return new ArrayList<TrainingUnit>();
	}

	@Override
	public TrainingUnitDetail getTrainingUnitWithDetails(int trainingUnitId) {
		// TODO, Chris: Implement this function correctly!
		return new TrainingUnitDetail();
	}
}
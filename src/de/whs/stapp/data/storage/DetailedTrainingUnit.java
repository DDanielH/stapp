package de.whs.stapp.data.storage;

import java.util.ArrayList;

/**
 * 
 * @author Christoph Inhestern
 * Enth�lt alle Daten einer Trainingseinheit, inklusive einer Liste
 * �ber alle Messwerten.
 */
public class DetailedTrainingUnit extends TrainingUnit {
	
	protected ArrayList<TrainingDetail> details = new ArrayList<TrainingDetail>();

	/**
	 * @return Gets the trainings details.
	 */
	public ArrayList<TrainingDetail> getDetails() {
		return details;
	}
}

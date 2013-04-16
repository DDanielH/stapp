package de.whs.stapp.database;

import java.util.ArrayList;

import de.whs.stapp.liveDataTracking.TrackedDataItem;

/**
 * 
 * @author Christoph Inhestern
 * Enth�lt alle Daten einer Trainingseinheit, inklusive einer Liste
 * �ber alle Messwerten.
 */
public class TrainingUnitDetail extends TrainingUnit{
	
	protected ArrayList<TrackedDataItem> trackedDataCollection = new ArrayList<TrackedDataItem>();

	/**
	 * @return the trackedDataCollection
	 */
	public ArrayList<TrackedDataItem> getTrackedDataCollection() {
		return trackedDataCollection;
	}
}

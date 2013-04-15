package de.whs.stapp.database;

import java.util.ArrayList;

import de.whs.stapp.liveDataTracking.TrackedDataItem;

/**
 * 
 * @author Christoph Inhestern
 *Enth�lt alle Daten einer Trainingseinheit, inklusive einer Liste
 *�ber alle Messwerten.
 */
public class TrainingUnitDetail extends TrainingUnit{

	
	public ArrayList<TrackedDataItem> trackedDataCollection = new ArrayList<TrackedDataItem>();
}

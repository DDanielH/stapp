package de.whs.stapp.data.access;

import java.util.ArrayList;
import java.util.List;

import de.whs.stapp.data.bluetooth.DataTracker;
import de.whs.stapp.data.bluetooth.TrackedDataItem;
import de.whs.stapp.data.bluetooth.TrackedDataListener;
import de.whs.stapp.data.storage.DatabaseAdapter;
import de.whs.stapp.data.storage.SessionDetail;
import de.whs.stapp.data.storage.TrainingSession;

/**
 * Verwaltet eine Trainingseinheit.
 * @author Chris
 */
public class TrainingController {
	
	private DataTracker tracker;
	private DatabaseAdapter database;	
	private TrackedDataListener dataListener;

	private TrainingState state = TrainingState.NEW;
	private TrainingSession currentTraining = new TrainingSession();
	
	private List<TrainingDetailListener> trainingDetailListeners =
			new ArrayList<TrainingDetailListener>();


	/**
	 * Erzeugt eine neue Instanz der {@link TrainingController} Klasse.
	 * @param tracker Eine {@link DataTracker} Implementierung.
	 * @param database Eine {@link DatabaseAdapter} Implementierung.
	 */
	public TrainingController(DataTracker tracker, DatabaseAdapter database) {		
		if (database == null)
			throw new IllegalArgumentException("databaseAdapter cannot be null");
		if (tracker == null)
			throw new IllegalArgumentException("serviceConnection cannot be null");

		this.database = database;
		this.tracker = tracker;
		
		initializeDataListener();
	}
	
	/**
	 * @return Die aktuelle {@link TrainingSession}.
	 */
	public TrainingSession getCurrentTrainingUnit() {
		return currentTraining;
	}

	/**
	 * @return Der Status der Trainingseinheit.
	 */
	public TrainingState getState() {
		return state;
	}

	/**
	 * Startet die Trainingseinheit.
	 */
	public void start() {
		if (state != TrainingState.NEW)
			return;

		state = TrainingState.RUNNING;
		currentTraining = database.newTrainingSession();

		tracker.registerListener(dataListener);
	}

	/**
	 * Beendet die aktuelle Trainingseinheit.
	 */
	public void stop() {
		if (state == TrainingState.FINISHED) 
			return;
		
		state = TrainingState.FINISHED;
		tracker.unregisterListener(dataListener);
	}

	/**
	 * Registriert den listener, so dass dieser �ber eingehende 
	 * {@link SessionDetail}s informiert wird.
	 * @param listener Der {@link TrainingDetailListener}.
	 */
	public void registerListener(TrainingDetailListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");

		if (!trainingDetailListeners.contains(listener))
			trainingDetailListeners.add(listener);
	}

	/**
	 * Entfernt einen registrierten listener wieder.
	 * @param listener Der registrierte {@link TrainingDetailListener}.
	 */
	public void unregisterTrackedDataListener(TrainingDetailListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");

		if (trainingDetailListeners.contains(listener))
			trainingDetailListeners.remove(listener);
	}

	private void initializeDataListener() {
		dataListener = new TrackedDataListener() {
			@Override
			public void trackData(TrackedDataItem dataItem) {
				assert dataItem != null : "dateItem cannot be null";
				if (state == TrainingState.RUNNING)
					processTrackedData(dataItem);
			}
		};
	}

	private void processTrackedData(TrackedDataItem dataItem) {		
		SessionDetail detail = createTrainingDetail(dataItem);

		notifyTrainingDetailListeners(detail);
		//database.storeSessionDetail(currentTraining.getTrainingUnitId(), detail);
	}
	
	private SessionDetail createTrainingDetail(TrackedDataItem dataItem) {
		SessionDetail detail = new SessionDetail();
		
		// TODO korrekt umrechnen!
		detail.setDistanceInMeter((int)dataItem.getDistanceInOne16thsMeter());
		detail.setHeartRate((int)dataItem.getHeartRateInBpm());
		detail.setNumberOfStrides((int)dataItem.getStrides());
		detail.setSpeedInMeterPerSecond((int)dataItem.getSpeedInOne256thsMeterPerSecond());
		
		return detail;
	}

	private void notifyTrainingDetailListeners(SessionDetail detail) {
		for (TrainingDetailListener listener : trainingDetailListeners)
			listener.trackTrainingDetail(detail);
	}
}

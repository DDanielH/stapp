package de.whs.stapp.presentation.viewmodels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;

import de.whs.stapp.data.storage.TrainingSession;

/***
 * Enth�lt alle Daten, die auf der HistoryView angezeigt werden.
 * 
 * @author Thomas
 * 
 */
public class History extends StappViewModel {

	private int distanceSum = 0;
	private int runCounter = 0;
	private long durationSum = 0;
	private ArrayList<Session> sessions = new ArrayList<Session>();

	/**
	 * Erstellt eine neue Instanz der History-Klasse. Dabei muss die Liste der
	 * Sessions �bergeben werden um die Membervariablen zu initialisieren.
	 * 
	 * @param sessions
	 *            Enth�lt die Liste der Trainingssession, die zur Aggregration
	 *            der �berblicksdaten genutzt werden.
	 */
	public History(ArrayList<TrainingSession> sessions) {

		Collections.sort(sessions);
		for (TrainingSession session : sessions) {

			distanceSum += session.getDistanceInMeters();
			runCounter++;
			durationSum += session.getDurationInMs();
			this.sessions.add(convertTrainingSessionToViewModel(session));
		}
	}

	private Session convertTrainingSessionToViewModel(TrainingSession session) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		
		return new Session(df.format(session.getTrainingDate()),
				(int)session.getDistanceInMeters(), session.getDurationInMs(), session.getSessionId());
	}

	/**
	 * @return Gibt die Distanz zur�ck.
	 */
	public int getDistanceSum() {
		return distanceSum;
	}

	/**
	 * @param distanceSum Setzt die Distanz.
	 */
	public void setDistanceSum(int distanceSum) {
		this.distanceSum = distanceSum;
	}

	/**
	 * @return Gibt die Anzahl der L�ufe zur�ck.
	 */
	public int getRunCounter() {
		return runCounter;
	}

	/**
	 * @param runCounter Setzt die Anzahl der L�ufe.
	 */
	public void setRunCounter(int runCounter) {
		this.runCounter = runCounter;
	}

	/**
	 * @return Gibt die gesamte Dauer zur�ck.
	 */
	public long getDurationSum() {
		return durationSum;
	}

	/**
	 * @param durationSum Setzt die gesamte Dauer.
	 */
	public void setDurationSum(long durationSum) {
		this.durationSum = durationSum;
	}

	/**
	 * @return Gibt die Sessions zur�ck.
	 */
	public ArrayList<Session> getSessions() {
		return sessions;
	}

	/**
	 * @param sessions Setzt die Sessions.
	 */
	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
}

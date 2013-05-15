package de.whs.stapp.presentation.viewmodels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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
	private LinkedHashMap<String, List<Session>> groupedSessions = new LinkedHashMap<String, List<Session>>();

	/**
	 * Erstellt eine neue Instanz der History-Klasse. Dabei muss die Liste der
	 * Sessions �bergeben werden um die Membervariablen zu initialisieren.
	 * 
	 * @param sessions
	 *            Enth�lt die Liste der Trainingssession, die zur Aggregration
	 *            der �berblicksdaten genutzt werden.
	 */
	public History(ArrayList<TrainingSession> sessions) {

		for (TrainingSession session : sessions) {

			distanceSum += session.getDistanceInMeters();
			runCounter++;
			durationSum += session.getDurationInMs();
			aggregateSession(session);
		}

	}

	private void aggregateSession(TrainingSession session) {
		String month = new SimpleDateFormat("MMM", Locale.GERMANY)
				.format(session.getTrainingDate());

		if (groupedSessions.containsKey(month)) {
			((List<Session>) groupedSessions.get(month))
					.add(convertTrainingSessionToViewModel(session));
		} else {
			List<Session> sessions = new ArrayList<Session>();
			sessions.add(convertTrainingSessionToViewModel(session));
			groupedSessions.put(month, sessions);
		}
	}

	private Session convertTrainingSessionToViewModel(TrainingSession session) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

		return new Session(df.format(session.getTrainingDate()),
				(int) session.getDistanceInMeters(), session.getDurationInMs(),
				session.getSessionId());
	}

	/**
	 * @return Gibt die Distanz zur�ck.
	 */
	public int getDistanceSum() {
		return distanceSum;
	}

	/**
	 * @return Gibt die Anzahl der L�ufe zur�ck.
	 */
	public int getRunCounter() {
		return runCounter;
	}

	/**
	 * @return Gibt die gesamte Dauer zur�ck.
	 */
	public long getDurationSum() {
		return durationSum;
	}

	/**
	 * @return Gibt die Sessions zur�ck.
	 */
	public LinkedHashMap<String, List<Session>> getSessions() {
		return groupedSessions;
	}
}

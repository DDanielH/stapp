package de.whs.stapp.presentation.viewmodels;


/**
 * ViewModel f�r die Ansicht Trainingseinheit.
 * @author Thomas
 *
 */
public class TrainingSession extends StappViewModel {
	
	private String distance;
	private String heartfrequence;
	
	/**
	 * Konstruktor zum Erzeugen einer Trainingseinheit.
	 * Enth�lt nur die Daten, die auf dem Ger�t angezeigt werden.
	 * @param distance Zur�ckgelegte Distanz.
	 * @param heartfrequence Aktuelle Herzfrequenz.
	 */
	public TrainingSession(int distance, int heartfrequence) {
		super();
		this.distance = Integer.toString(distance);
		this.heartfrequence = Integer.toString(heartfrequence);
	}
	
	/**
	 * Gibt die zur�ckgelegte Distanz der Trainingseinheit zur�ck.
	 * @return Zur�ckgelegte Distanz.
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * Gibt die Herzfrequenz der Trainingseinheit zur�ck.
	 * @return Aktuelle Herzfrequenz.
	 */
	public String getHeartfrequence() {
		return heartfrequence;
	}
}

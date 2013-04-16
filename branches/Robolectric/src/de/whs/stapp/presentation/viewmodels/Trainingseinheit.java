package de.whs.stapp.presentation.viewmodels;

import java.math.BigDecimal;

/**
 * ViewModel f�r die Ansicht Trainingseinheit.
 * @author Thomas
 *
 */
public class Trainingseinheit extends StappViewModel {
	
	private String distance;
	private String heartfrequence;
	
	/**
	 * Konstruktor zum Erzeugen einer Trainingseinheit.
	 * Enth�lt nur die Daten, die auf dem Ger�t angezeigt werden.
	 * @param distance Zur�ckgelegte Distanz.
	 * @param heartfrequence Aktuelle Herzfrequenz.
	 */
	public Trainingseinheit(BigDecimal distance, int heartfrequence) {
		super();
		this.distance = distance.toString();
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

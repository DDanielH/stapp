package de.whs.stapp.presentation.viewmodels;

import java.math.BigDecimal;

/**
 * ViewModel f�r die Ansicht Trainingseinheit.
 * @author Thomas
 *
 */
public class Trainingseinheit extends StappViewModel {
	
	private BigDecimal distance;
	private int heartfrequence;
	
	/**
	 * Konstruktor zum Erzeugen einer Trainingseinheit.
	 * Enth�lt nur die Daten, die auf dem Ger�t angezeigt werden.
	 * @param distance Zur�ckgelegte Distanz.
	 * @param heartfrequence Aktuelle Herzfrequenz.
	 */
	public Trainingseinheit(BigDecimal distance, int heartfrequence) {
		super();
		this.distance = distance;
		this.heartfrequence = heartfrequence;
	}
	
	/**
	 * Gibt die zur�ckgelegte Distanz der Trainingseinheit zur�ck.
	 * @return Zur�ckgelegte Distanz.
	 */
	public BigDecimal getDistance() {
		return distance;
	}

	/**
	 * Speichert die zur�ckgelegte Distanz der Trainingseinheit.
	 * @param distance Zur�ckgelegte Distanz
	 */
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	/**
	 * Gibt die Herzfrequenz der Trainingseinheit zur�ck.
	 * @return Aktuelle Herzfrequenz.
	 */
	public int getHeartfrequence() {
		return heartfrequence;
	}

	/**
	 * Speichert die Herzfrequenz der aktuellen Trainingseinheit.
	 * @param heartfrequence Aktuelle Herzfrequenz.
	 */
	public void setHeartfrequence(int heartfrequence) {
		this.heartfrequence = heartfrequence;
	}
}

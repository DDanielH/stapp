package de.whs.stapp.presentation;

import android.content.Context;

/**
 * Stellt alle Funktionen zur Verf�gung, die aus dem Javascript im Android
 * Container aufgerufen werden k�nnen.
 * 
 * @author Thomas
 * 
 */
public class TrainingUnitWebAppInterface extends StappWebAppInterface {

	/**
	 * Default Konstruktor. Instantiiert ein neues WebApp Interface auf Basis {@link StappWebAppInterface}.
	 * @param mContext Kontext des WebApp Interface.
	 */
	public TrainingUnitWebAppInterface(Context mContext) {
		super(mContext);
	}

}

package de.whs.stapp.presentation.webviews;

import android.content.Context;

/**
 * Stellt alle Funktionen zur Verf�gung, die aus dem Javascript im Android
 * Container aufgerufen werden k�nnen.
 * 
 * @author Thomas
 * 
 */
public class TrainingSessionWebAppInterface extends StappWebAppInterface {

	/**
	 * Default Konstruktor. Instantiiert ein neues WebApp Interface auf Basis {@link StappWebAppInterface}.
	 * @param mContext Kontext des WebApp Interface.
	 */
	public TrainingSessionWebAppInterface(Context mContext) {
		super(mContext);
	}
}

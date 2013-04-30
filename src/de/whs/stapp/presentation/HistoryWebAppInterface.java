package de.whs.stapp.presentation;

import de.whs.stapp.presentation.views.ChartActivity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

/**
 * Stellt alle Funktionen zur Verf�gung, die aus dem Javascript im Android
 * Container aufgerufen werden k�nnen.
 * 
 * @author Thomas
 * 
 */
public class HistoryWebAppInterface extends StappWebAppInterface {

	/**
	 * Default Konstruktor. Instantiiert ein neues WebApp Interface auf Basis
	 * {@link StappWebAppInterface}.
	 * 
	 * @param mContext
	 *            Kontext des WebApp Interface.
	 */
	public HistoryWebAppInterface(Context mContext) {
		super(mContext);
	}

	/**
	 * Wird aufgerufen wenn eine TrainingsSession ausgew�hlt wurde.
	 * 
	 * @param sessionID
	 *            TrainingsSessionID der Session.
	 */
	@JavascriptInterface
	public void selectTrainingSession(int sessionID) {
		Intent intent = new Intent(mContext, ChartActivity.class);
		intent.putExtra("sessionID", sessionID);
		mContext.startActivity(intent);
	}
}

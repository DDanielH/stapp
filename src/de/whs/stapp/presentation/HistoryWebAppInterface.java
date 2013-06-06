package de.whs.stapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import de.whs.stapp.StappActivity;
import de.whs.stapp.data.access.DataAccess;
import de.whs.stapp.data.access.StappStorageAccess;
import de.whs.stapp.presentation.views.ChartActivity;

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
	public void selectTrainingSession(String sessionID) {
		
		Intent intent = new Intent(mContext, ChartActivity.class);
		intent.putExtra("sessionID", Integer.parseInt(sessionID));
		mContext.startActivity(intent);
	}
	
	
	/**
	 * Wird aufgerufen wenn eine TrainingsSession gel�scht werden soll.
	 * 
	 * @param sessionID
	 *            TrainingsSessionID der Session.
	 */
	@JavascriptInterface
	public void deleteTrainingSession(String sessionID) {
		
		
		StappActivity sa = (StappActivity) mContext;
		DataAccess dataAccess = sa.getStappDataAccess();
		
		dataAccess.deleteTrainingSession(Integer.parseInt(sessionID));
	}
	
	
}

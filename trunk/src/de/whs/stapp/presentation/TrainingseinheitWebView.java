package de.whs.stapp.presentation;

import android.content.Context;
import de.whs.stapp.presentation.viewmodels.Trainingseinheit;

/**
 * Bietet die Funktionen einer WebView an. Zus�tzlich gibt es eine
 * Spezialisierung auf die Bed�rfnisse der Trainginseinheit-Seite.
 * 
 * @author Thomas
 * 
 */
public class TrainingseinheitWebView extends StappWebView {

	/**
	 * Standardkonstruktor, der die Trainingseinheit- Seite l�dt.
	 * 
	 * @param context
	 *            Kontext der zu rendernden WebView.
	 */
	public TrainingseinheitWebView(Context context) {
		super(context);

		//TODO: Laden der Webseite von einem Server
		// Abh�ngigkeit von der Internetverbindung einbauen
		this.loadUrl("file:///android_asset/www/Trainingseinheit.html");
	}

	/**
	 * �bergibt die ge�nderten Trainingsdaten an das Javascript, 
	 * sodass dieses sich updaten kann.
	 * 
	 * @param trainingData
	 *            Die ge�nderten Daten der Trainingseinheit.
	 */
	public void updateTrainingData(Trainingseinheit trainingData) {

		String functionCall = trainingData
			.getJavascriptFunctionCall("stapp.updateTraining()");
		this.loadUrl(functionCall);
	}
	
	/**
	 * Startet eine neue Trainingseinheit.
	 */
	public void startTraining(){
		
		this.loadUrl("javascript:stapp.startTraining()");
	}

}

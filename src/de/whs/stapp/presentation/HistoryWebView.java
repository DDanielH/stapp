package de.whs.stapp.presentation;

import android.content.Context;
import de.whs.stapp.presentation.viewmodels.History;

/**
 * Bietet die Funktionen einer WebView an. Zus�tzlich gibt es eine
 * Spezialisierung auf die Bed�rfnisse der Verlauf-Seite.
 * 
 * @author Thomas
 * 
 */
public class HistoryWebView extends StappWebView {

	/**
	 * Standard-Konstruktor f�r die Verlauf WebView.
	 * 
	 * @param context
	 *            Kontext der WebView.
	 */
	public HistoryWebView(Context context) {
		super(context, new HistoryWebAppInterface(context));

		this.loadUrl(Constants.HTML_LOCAL_VERLAUF);
	}

	/**
	 * Setzt die Daten der Verlaufsseite.
	 * 
	 * @param data
	 *            Enth�lt die Daten, die in der Verlaufsseite angezeigt werden.
	 */
	public void setHistoryData(History data) {

		String functionCall = Javascript.getFunctionCall(Constants.JS_HISTORY_SET, data);
		this.loadUrl(functionCall);
	}
}

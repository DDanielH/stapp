package de.whs.stapp.presentation;

import de.whs.stapp.presentation.helper.Constants;
import android.content.Context;

/**
 * Bietet die Funktionen einer WebView an. Zus�tzlich gibt es eine
 * Spezialisierung auf die Bed�rfnisse der Verlauf-Seite.
 * @author Thomas
 *
 */
public class HistoryWebView extends StappWebView {

	/**
	 * Standard-Konstruktor f�r die Verlauf WebView.
	 * @param context Kontext der WebView.
	 */
	public HistoryWebView(Context context) {
		super(context, new HistoryWebAppInterface(context));

		this.loadUrl(Constants.HTML_LOCAL_TRAININGSEINHEIT);
	}

}

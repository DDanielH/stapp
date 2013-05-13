package de.whs.stapp.presentation;

import android.content.Context;
import de.whs.stapp.presentation.viewmodels.ChartData;

/**
 * Stellt eine WebView f�r die Charts zur Verf�gung. Enth�lt Funktionen zum
 * Laden der Webseite und enth�lt die Funktionen zum Javascript.
 * 
 * @author Thomas
 * 
 */
public class ChartWebView extends StappWebView {

	/**
	 * Standardkonstruktor der ChartWebView.
	 * 
	 * @param context
	 *            Kontext der WebView
	 */
	public ChartWebView(Context context) {
		super(context);

		this.loadUrl(Constants.HTML_LOCAL_CHARTS);
	}

	/**
	 * L�dt zus�tzlich die WebView mit der passenden URL.
	 * 
	 * @param context
	 *            Kontext der WebView.
	 * @param stappWebAppInterface
	 *            Interface zur Kommunikation aus Javascript mit Android.
	 */
	public ChartWebView(Context context,
			StappWebAppInterface stappWebAppInterface) {
		super(context, stappWebAppInterface);
		this.loadUrl(Constants.HTML_LOCAL_CHARTS);
	}

	/**
	 * Ruft die setCharts Funktion im Javascript auf und �bergibt die Daten aus
	 * der Datenbank.
	 * 
	 * @param data
	 *            Daten einer bestimmten Datenreihe aus der Datenbank.
	 */
	public void setChartData(ChartData data) {

		String functionCall = Javascript.getFunctionCall(
				Constants.JS_CHARTS_SET, data); 
		this.loadUrl(functionCall);
	}
}

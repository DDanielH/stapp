package de.whs.stapp.presentation;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/***
 * Schnittstelle vom Javascript zu Java.
 * Beeinhaltet alle Funktionen, die aus dem Javascript am Objekt
 * Android aufgerufen werden k�nnen.
 * @author Thomas
 *
 */
public class StappWebAppInterface {
	
	private Context mContext;
	
	/**
	 * Standard-Konstruktor f�r das WepAppInterface.
	 * @param mContext Kontext auf dem die Funktionen ausgef�hrt werden.
	 */
	public StappWebAppInterface(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	/***
	 * Schnittstelle vom Javascript zu Java um 
	 * einen Toast zu erstellen. Kann vom Javascript
	 * am Objekt Android aufgerufen werden um Betriebsystemfunktionen
	 * auszuf�hren.
	 * @param toast Anzuzeigender String
	 */
	@JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}

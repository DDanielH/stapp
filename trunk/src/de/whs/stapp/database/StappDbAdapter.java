package de.whs.stapp.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import de.whs.stapp.data.bluetooth.TrackedDataItem;

/**
 * 
 * @author Christoph
 * 
 * Klasse f�r den Zugriff auf die Datenbank und die zugeh�rigen Funktionen
 * 
 */

public class StappDbAdapter extends SQLiteOpenHelper implements DatabaseAdapter {

	//Android standard Pfad f�r App Datenbanken
    private static String dbPath = "/data/data/de.whs.stapp/databases/";
    private static String dbName = "NamenEingeben.db"; 
    private SQLiteDatabase stappDb; 
    private final Context stappContext;	
    private final int streamSize = 1024;
    
    
    //Konstanten f�r die Datenbank Bezeichner
    
    
	
    /**
     * @author Christoph
     * Konstruktor 
     * Nimmt den Context entgegen und speichert diesen um sp�ter auf
     * die ressourcen und assets zugreifen zu k�nnen
     * 
     * @param context , Context in dem die Klasse instanziiert wird.
     * 
     */
	public StappDbAdapter(Context context) {
		super(context, dbName, null, 1);
        this.stappContext = context; 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, 
				int oldVersion,	int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * @author Christoph Inhestern
	 * Erstellt eine leere Datenbank auf dem System und �berschreibt diese mit
	 * dem Inhalt der Datenbank aus dem asset Ordner.
	 * 
	 * @throws IOException , f�r den Fall, dass das kopieren der Datenbank fehlschl�gt.
	 */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(!dbExist){
    		//By calling this method an empty database will be created 
    		//into the default system pat of the application so it is possible 
    		//to overwrite that database with the STAPP Database.
        	
    		this.getReadableDatabase();
    		
    		try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}

    }
    
    
    /**
     * @author Christoph Inhestern
     * �berpr�ft ob die Datenbank bereit vorhanden ist, um zu
     * vermeiden, dass diese bei jedem App start neu kopiert wird.
     * 
     * @return true falls vorhanden, false falls nicht vorhanden
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = dbPath + dbName;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		throw new Error ("Error while checking for Database");
    	}
 
    	if(checkDB != null){
    		checkDB.close();
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    
    /**
     * @author Christoph Inhestern
     * Kopiert die Datenbank vom lokalen asset Ordner zu der 
     * neu erstellten leeren Datenbank im Systemordner.
     * Kopiert wird dies per Bytestream.
     * @throws IOException , Exception Handler f�r das kopieren
     */
    private void copyDataBase() throws IOException{
 
    	//Open the local db as the input stream
    	InputStream myInput = stappContext.getAssets().open(dbName);
 
    	// Path to the just created empty db
    	String outFileName = dbPath + dbName;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[streamSize];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    }
    
    
    /**
    * @author Christoph Inhestern
    * �ffnet die Datenbank.
    * stappDb ist der Handler auf die Datenbank.
    */
    public void openDataBase() {
   	 
    	//Open the database
        String myPath = dbPath + dbName;
    	stappDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    
    
    /**
    * @author Christoph Inhestern
    * Schlie�t die Datenbank nach der Benutzung.
    */
    @Override
	public synchronized void close() {
    	stappDb.close();
		super.close();
    }
    
    
    /**
     * @author Christoph Inhestern
     * Gibt alle Daten einer Trainingseinheit zur�ck. Dies enth�lt
     * alle vorhandenen Messwerte zu einer Einheit.
     * 
     * @param id , ID der detailliert anzuzeigenden Trainingseinheit.
     * @return
     */
    public TrainingUnitDetail getTrainingUnitDetail(int id) {
    	TrainingUnitDetail result = new TrainingUnitDetail();
    	
    	// TODO 
    		
    	return result;
    }
    
    /**
     * @author Christoph Inhestern
     * Liefert eine Liste aller Trainingseinheiten zur�ck.
     * @return
     */
    public List<TrainingUnit> getTrainingUnitsOverview() {
        // TODO    	
    	return null;
    }
    
    
    /**
     * @author Christoph Inhestern
     * Die Methode schreibt Werte zu einer Trainingseinheit in die
     * Datenbank.
     * 
     * @param trainingUnitId Id der aktuell laufenden Trainingseinheit.
     * @param dataItem Die aktuellen Messwerte, die in der Datenbank gespeichert werden sollen. 
     * @return
     */
    public boolean saveTrackedDataItem (int trainingUnitId, TrackedDataItem dataItem) {   	
    	ContentValues val = new ContentValues();
    	val.put("ID", trainingUnitId);
    	
    	// TODO

    	if (stappDb.insert("TABBELLE MIT WERTEN", null, val) == -1)   	
    		return false;
    	
    	return true;
    }
    
    /**
     * @author Christoph Inhestern
     * Die Methode legt einen neuen Eintrag f�r eine Trainingseinheit
     * in der Datenbank (in der Tabelle "TrainingsUnit") an.
     * 
     * @return
     */
    public TrainingUnit createNewTrainingUnit() {
    	TrainingUnit result = new TrainingUnit();
    	
    	//TODO
    	
    	return result;    	
    }
}

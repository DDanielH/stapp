package de.whs.stapp.data.access;

/**
 * Definiert die verschiedenen Stati 
 * f�r eine {@link TrainingSession}.
 * @author Chris
 */
public enum TrainingState {
	/**
	 * Angelegt, aber noch nicht gestartet.
	 */
	NEW,
	/**
	 * Training l�uft / findet aktiv statt.
	 */
	RUNNING,
	/**
	 * Training ist pausiert / unterbrochen.
	 */
	PAUSED,
	/**
	 * Training ist abgeschlossen.
	 */
	FINISHED
}

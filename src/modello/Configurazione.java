package modello;

import java.awt.Point;

import modello.pedine.ElementoScacchiera;


/**
 * L'interfaccia configurazione, che memorizza lo stato della scacchiera.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public interface Configurazione {

	/**
	 * Restituisce l'elemento che si trova alla posizione punto.
	 * @param punto il punto.
	 * @return l'elemento.
	 */
	public ElementoScacchiera a(Point punto);
	
	/**
	 * Imposta l'elemento alla posizione punto.
	 * @param punto il punto in cui inserire l'elemento.
	 * @param valore il valore da inserire.
	 */
	public void setPosizione(Point punto, ElementoScacchiera valore);
	
	/**
	 * Crea una copia della configurazione.
	 * @return la copia.
	 */
	public Configurazione creaCopia();
}

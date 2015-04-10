package controllore;

import java.awt.Point;

/**
 * L'interfaccia del Controllore
 * @author Marco Panato - Nicola Dall'Ora
 *
 */
public interface Controllore {

	/**
	 * Inizia una nuova partita.
	 */
	public void nuovaPartita();
	
	/**
	 * Esce dall'applicazione.
	 */
	public void esci();
	
	/**
	 * Indica un click sulla casella identificata da 'punto'.
	 * @param punto le coordinate della casella cliccata.
	 */
	public void click(Point punto);
	
}

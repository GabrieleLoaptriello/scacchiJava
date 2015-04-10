package modello;

import java.awt.Point;
import java.util.HashSet;


/**
 * L'interfaccia modello, che memorizza le informazioni di gioco.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public interface Modello {
	
	/**
	 * Costante per il colore bianco delle pedine.
	 */
	final public static boolean BIANCO=true;
	
	/**
	 * Costante per il colore nero delle pedine.
	 */
	final public static boolean NERO=false;
	
	/**	
	 * @return restituisce lo stato attuale della scacchiera come Configurazione.
	 */
	public Configurazione getConfigurazioneCorrente();
	
	/**
	 * Imposta la configurazione corrente del modello.
	 * @param c la configurazione da memorizzare.
	 */
	public void setConfigurazione(Configurazione c);

	/**
	 * Restituisce il punto in cui si trova la pedina selezionata dall'utente
	 * @return il punto.
	 */
	public Point getPuntoSelezionato();
	
	/**
	 * Imposta il punto in cui si trova la pedina selezionata dall'utente.
	 * @param punto il punto selezionato.
	 */
	public void setSelezionato(Point punto);
	
	/**
	 * Imposta la lista di mosse che l'utente può fare al prossimo click.
	 * @param mosse le mosse disponibili.
	 */
	public void setMosseDisponibili(HashSet<Point> mosse);
	
	/**
	 * Restituisce le mosse che l'utente può effettuare dopo aver selezionato una pedina.
	 * @return le mosse disponibili.
	 */
	public HashSet<Point> getMosseDisponibili();
}

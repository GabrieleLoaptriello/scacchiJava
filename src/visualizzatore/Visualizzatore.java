package visualizzatore;

import java.awt.Point;
import java.util.HashSet;

import modello.Modello;
import modello.pedine.TipiPedine;
import controllore.Controllore;


/**
 * L'interfaccia Visualizzatore, che rappresenta graficamente la scacchiera,
 * gestita dal Controllore.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public interface Visualizzatore {

	public Modello getModello();
	
	public void setControllore(Controllore c);
	
	/**
	 * Indica una variazione dei dati del modello.
	 */
	public void modelloCambiato();
	
	/**
	 * Mostra una finestra indicando la vittoria di un giocatore.
	 * @param quale il giocatore che ha vinto.
	 * @param tempoBianchi tempo totale del giocatore bianco
	 * @param tempoNeri tempo totale del giocatore nero
	 */
	public void partitaFinita(boolean quale, int tempoBianchi, int tempoNeri);
	
	/**
	 * Mostra una finestra indicando che la partita è patta.
	 * @param causa il motivo per cui la partita è finita in pareggio.
	 * @param tempoBianchi il tempo totale del giocatore bianco.
	 * @param tempoNeri il tempo totale del giocatore nero.
	 */
	public void partitaPatta(String causa, int tempoBianchi, int tempoNeri);
	
	/**
	 * Mostra una finestra indicando lo scacco per un giocatore.
	 * @param quale il giocatore che è sotto scacco.
	 */
	public void scacco(boolean quale);
	
	/**
	 * Evidenzia le celle corrispondenti ai punti indicati.
	 * @param mosseDisponibili le coordinate delle celle da evidenziare
	 */
	public void evidenziaCelle(HashSet<Point> mosseDisponibili);
	
	/**
	 * Evidenzia la cella indicata.
	 * @param punto le coordinate della cella da evidenziare.
	 */
	public void evidenziaCella(Point punto);
	

	/**
	 * Fa tornare tutte le celle all'aspetto iniziale.
	 */
	public void resettaBordiCelle();
	
	/**
	 * Fa tornare le celle indicate all'aspetto iniziale.
	 * @param punti le celle da resettare.
	 */
	public void resettaBordiCelle(HashSet<Point> punti);
	
	/**
	 * Fa tornare la cella indicata all'aspetto iniziale.
	 * @param punto la cella da resettare.
	 */
	public void resettaBordoCella(Point punto);
	
	/**
	 * Richiede all'utente quale tipo di pedina vuole riottenere.
	 * @return il tipo di pedina scelto
	 */
	public TipiPedine scegliPedinaDaRecuperare();
	
	/**
	 * Indica alla grafica quale giocatore deve muovere e il suo tempo.
	 * @param info la stringa che contiene le informazioni.
	 */
	public void modificaInformazioni(String info);
	
	/**
	 * Chiede all'utente se vuole iniziare una nuova partita.
	 * @return true se l'utente vuole iniziarne una nuova.
	 */
	public boolean richiediNuovaPartita();
	
}

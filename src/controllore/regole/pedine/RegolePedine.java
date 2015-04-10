package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;
import modello.pedine.*;

/**
 * Le regole delle pedine.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public abstract class RegolePedine {

	/**
	 * Le mosse disponibili per una pedina.
	 */
	private HashSet<Point> mosse;
	
	/**
	 * Costruisce un oggetto RegolePedine preallocando un certo numero di mosse.
	 * @param quante il numero di mosse da preallocare.
	 */
	public RegolePedine(int quante) {
		mosse = new HashSet<Point>(quante);
	}
	
	/**
	 * Aggiunge la mossa p all'insieme delle mosse disponibili.
	 * @param p la mossa da aggiungere.
	 */
	protected void aggiungiMossa(Point p) {
			mosse.add(p);
	}
	
	/**
	 * @return le mosse disponibili.
	 */
	protected HashSet<Point> getMosse() {
		return mosse;
	}
	
	/**
	 * Costruisce l'oggetto RegolePedine corrispondente alla pedina data.
	 * @param p la pedina.
	 * @return l'oggetto corrispondente.
	 */
	public static RegolePedine getRegole(Pedina p) {
		if (p instanceof Pedone)
			return new RegolePedone();
		if (p instanceof Torre)
			return new RegoleTorre();
		if (p instanceof Cavallo)
			return new RegoleCavallo();
		if (p instanceof Alfiere)
			return new RegoleAlfiere();
		if (p instanceof Regina)
			return new RegoleRegina();
		if (p instanceof Re)
			return new RegoleRe();
		
		// non ci arrivo mai!
		return null;
	}
	
	/**
	 * Indica se il punto dato si trova all'interno della scacchiera.
	 * @param punto il punto da esaminare.
	 * @return true se il punto si trova nella scacchiera.
	 */
	public static boolean isDentroScacchiera(Point punto) {
		return punto.x > -1 && punto.x < 8 && punto.y > -1 && punto.y < 8;
	}
	
	/**
	 * Indica se il punto dato non contiene pedine.
	 * @param conf la configurazione corrente.
	 * @param punto il punto da esaminare.
	 * @return true se nella configurazione corrente in quel punto non ci sono pedine.
	 */
	public static boolean vuota(Configurazione conf, Point punto) {
		return conf.a(punto) instanceof NessunaPedina;
	}
	
	/**
	 * Indica se il punto dato contiene pedine del colore opposto a colore.
	 * @param conf la configurazione corrente.
	 * @param punto il punto da esaminare.
	 * @param colore il colore della mia pedina.
	 * @return true se nella configurazione corrente in quel punto si trovano pedine
	 * 		   di colore diverso.
	 */
	public static boolean pedinaDiAltroColore(Configurazione conf, Point punto, boolean colore) {
		return !vuota(conf,punto) && ((Pedina)conf.a(punto)).colore() != colore;
	}
	
	/**
	 * Indica se il punto dato contiene pedine del colore dato.
	 * @param conf la configurazione corrente.
	 * @param punto il punto da esaminare.
	 * @param colore il colore della mia pedina.
	 * @return true se nella configurazione corrente in quel punto si trovano pedine
	 * 		   del colore dato.
	 */
	public static boolean pedinaDiStessoColore(Configurazione conf, Point punto, boolean colore) {
		return pedinaDiAltroColore(conf, punto, !colore);
	}
	
	/**
	 * Restituisce le mosse disponibili per questa pedina.
	 * @param conf la configurazione corrente.
	 * @param posizione la posizione in cui si trova la pedina.
	 * @param colore il colore della pedina.
	 * @return le mosse fattibili come ArrayList. 
	 */
	public abstract HashSet<Point> getMosseDisponibili(Configurazione conf, Point posizione, boolean colore);
	
}

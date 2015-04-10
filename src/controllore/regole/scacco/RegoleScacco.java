package controllore.regole.scacco;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;

import modello.Configurazione;
import modello.pedine.ElementoScacchiera;
import modello.pedine.NessunaPedina;
import modello.pedine.Pedina;
import modello.pedine.Re;
import controllore.regole.pedine.RegolePedine;


/**
 * Le regole di scacchi e scacchi matti.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleScacco {
	
	/**
	 * Indica se il giocatore colore si trova in scacco matto.
	 * @param conf la configurazione corrente.
	 * @param colore il colore del giocatore.
	 * @return true se il giocatore colore ha perso.
	 */
	public boolean isScaccoMatto(Configurazione conf, boolean colore) {		
		// Scorro la scacchiera in cerca delle pedine delle mie pedine
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Point punto = new Point(i, j);
				
				// Se trovo una pedina del mio colore
				if (conf.a(punto) instanceof Pedina && ((Pedina)conf.a(punto)).colore() == colore){
					// Se c'Ã¨ almeno una mossa
					if (!verificaMosse(conf, punto).isEmpty()){
						return false;
					}
				}
			}
		
		// Per ogni pedina non posso effettuare nessuna mossa quindi sono in scacco matto
		return true;
	}

	/**
	 * Restituisce la posizione del Re del colore opposto a quello dato.
	 * @param conf la configurazione corrente.
	 * @param colore il colore del giocatore.
	 * @return la posizione del re.
	 */
	private Point getPosizioneReOpposto(Configurazione conf, boolean colore) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Point punto = new Point(i, j);
				if (conf.a(punto) instanceof Re
						&& ((Re) conf.a(punto)).colore() != colore) {
					return punto;
				}
			}
		return null;
	}

	/**
	 * Indica se nella configurazione corrente il giocatore colore ha messo in scacco l'avversario.
	 * @param conf la configurazione corrente.
	 * @param colore il colore del giocatore.
	 * @return true se nella configurazione corrente il re dell'altro giocatore
	 *         si trova in scacco.
	 */
	public boolean isScacco(Configurazione conf, boolean colore) {
		Point posizioneReOpposto = getPosizioneReOpposto(conf, colore);

		// Scorro in cerca delle mie pedine e vedo se il re Ã¨ in scacco
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				ElementoScacchiera elem = conf.a(new Point(i, j));
				if (elem instanceof Pedina) {
					Pedina ped = (Pedina) elem;
					if (ped.colore() == colore) {
						HashSet<Point> mosse = RegolePedine.getRegole(ped)
								.getMosseDisponibili(conf, new Point(i, j),
										colore);
						if (mosse.contains(posizioneReOpposto))
							return true;
					}
				}
			}

		return false;
	}

	/**
	 * Data una configurazione e una posizione, elabora le mosse disponibili
	 * della pedina eliminando quelle che causerebbero uno scacco.
	 * @param conf
	 *            la configurazione corrente.
	 * @param posizionePedina
	 *            la posizione della pedina da muovere.
	 * @return una lista di mosse che possono essere effettuate.
	 */
	public HashSet<Point> verificaMosse(Configurazione conf, Point posizionePedina) {
		Pedina p = (Pedina)conf.a(posizionePedina); // è sempre una pedina
		HashSet<Point> mosse = RegolePedine.getRegole(p).getMosseDisponibili(
				conf, posizionePedina, p.colore());
		Iterator<Point> it = mosse.iterator();
		
		// Elimino le mosse che mandano in scacco il giocatore.
		while (it.hasNext()) {
			Point mossa = it.next();
			if (provaMossa(conf, posizionePedina, mossa)) {
				it.remove();
			}
		}
		
		return mosse;
	}

	/**
	 * Prova a muovere la pedina in posizioneIniziale verso posizioneFinale.
	 * Indica se dopo la mossa il Re del colore di p si trova in scacco.
	 * @param conf
	 *            la configurazione su cui operare.
	 * @param posizioneIniziale
	 *            la posizione di partenza della pedina.
	 * @param posizioneFinale
	 *            la posizione di arrivo della pedina.
	 * @return true se la mossa elimina lo scacco, false se lo scacco rimane.
	 */
	private boolean provaMossa(Configurazione conf,
			Point posizioneIniziale, Point posizioneFinale) {
		Pedina p = (Pedina)conf.a(posizioneIniziale); // è sempre una pedina
		// Copia della configurazione
		Configurazione copiaConf = conf.creaCopia();

		// Esecuzione della mossa
		copiaConf.setPosizione(posizioneFinale, conf.a(posizioneIniziale));
		copiaConf.setPosizione(posizioneIniziale, new NessunaPedina());

		// Controllo scacco
		return isScacco(copiaConf, !p.colore());
	}

}

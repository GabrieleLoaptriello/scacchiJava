package modello;

import java.awt.Point;
import java.util.HashSet;

import visualizzatore.Visualizzatore;


/**
 * Il modello usato dall'applicazione.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class ModelloScacchi implements Modello {

	private Visualizzatore visualizzatore;
	
	/**
	 * La configurazione della scacchiera memorizzata.
	 */
	private Configurazione configurazione;
	
	/**
	 * La posizione della pedina selezionata.
	 */
	private Point selezionato;
	
	/**
	 * Le mosse a disposizione dell'utente.
	 */
	private HashSet<Point> mosseDisponibili;
	
	public ModelloScacchi(Visualizzatore v) {
		this.visualizzatore = v;
	}
	
	@Override
	public Configurazione getConfigurazioneCorrente() {
		return configurazione;
	}

	@Override
	public void setConfigurazione(Configurazione c) {
		this.configurazione = c;
		visualizzatore.modelloCambiato();
	}

	@Override
	public Point getPuntoSelezionato() {
		return selezionato;
	}

	@Override
	public void setSelezionato(Point punto) {
		selezionato = punto;
	}

	@Override
	public void setMosseDisponibili(HashSet<Point> mosse) {
		mosseDisponibili = mosse;
	}

	@Override
	public HashSet<Point> getMosseDisponibili() {
		return mosseDisponibili;
	}

}

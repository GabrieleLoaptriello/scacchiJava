package controllore;

import java.awt.Point;
import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import modello.Configurazione;
import modello.ConfigurazioneMatrice;
import modello.Modello;
import modello.pedine.NessunaPedina;
import modello.pedine.Pedina;
import modello.pedine.Pedone;
import modello.pedine.Re;
import modello.pedine.TipiPedine;
import visualizzatore.Visualizzatore;
import controllore.regole.scacco.RegoleScacco;

/**
 * Il controllore del gioco.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class ControlloreScacchi implements Controllore {

	private Visualizzatore visualizzatore;
	private Modello modello;

	/**
	 * Un timer per la gestione dei tempi dei giocatori.
	 */
	private Timer timer;

	/**
	 * Indica quale giocatore deve muovere.
	 */
	private boolean turno;
	
	/**
	 * Indica se una pedina è selezionata o meno.
	 */
	private boolean pedinaSelezionata;
	
	/**
	 * Indica se la partita è finita o meno.
	 */
	private boolean partitaFinita;

	/**
	 * Indica il tempo totale del giocatore bianco.
	 */
	private int tempoBianchi;
	
	/**
	 * Indica il tempo totale del giocatore nero.
	 */
	private int tempoNeri;
	
	/**
	 * Indica il numero di mosse effettuate senza che venga
	 * mosso un pedone o senza che venga mangiata una pedina.
	 */
	private int numeroMosseSenzaMangiateSenzaPedone;
	
	public ControlloreScacchi(Visualizzatore v) {
		this.visualizzatore = v;
		modello = v.getModello();

		inizializzaNuovaPartita();
	}

	/**
	 * Imposta le variabili del controllore in modo da predisporre una nuova partita.
	 */
	private void inizializzaNuovaPartita() {
		// Iniziano sempre i bianchi
		turno = Modello.BIANCO;
		pedinaSelezionata = false;
		partitaFinita = false;
		tempoBianchi = 0;
		tempoNeri = 0;
		numeroMosseSenzaMangiateSenzaPedone = 0;

		avviaTimer();

		visualizzatore.resettaBordiCelle();
		modello.setConfigurazione(new ConfigurazioneMatrice());
	}

	/**
	 * Chiamato quando la partita è finita.
	 */
	private void partitaFinita() {
		fermaTimer();
		visualizzatore.partitaFinita(!turno,tempoBianchi,tempoNeri);
		partitaFinita = true;
		
		if (visualizzatore.richiediNuovaPartita())
			this.nuovaPartita();
	}
	
	/**
	 * Chiamato quando la partita è finita in pareggio.
	 * @param causa la causa del pareggio.
	 */
	private void partitaPatta(String causa) {
		fermaTimer();
		visualizzatore.partitaPatta(causa, tempoBianchi, tempoNeri);
		partitaFinita = true;
		
		if (visualizzatore.richiediNuovaPartita())
			this.nuovaPartita();
	}

	/**
	 * Avvia il timer.
	 */
	private void avviaTimer() {
		visualizzatore.modificaInformazioni("TOCCA AL BIANCO: 00:00");

		timer = new Timer("TempoDiGioco");
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if(turno == Modello.BIANCO){
					tempoBianchi++;
					visualizzatore.modificaInformazioni(String.format("TOCCA AL BIANCO: %02d:%02d", tempoBianchi/60, tempoBianchi % 60));
				}
				else{
					tempoNeri++;
					visualizzatore.modificaInformazioni(String.format("TOCCA AL NERO: %02d:%02d", tempoNeri/60, tempoNeri % 60));
				}
			}
		};
		timer.scheduleAtFixedRate(task,
				Date.from(Instant.now().plusSeconds(1)), 1000);
	}

	/**
	 * Ferma il timer.
	 */
	private void fermaTimer() {
		timer.cancel();
	}
	
	/**
	 * Seleziona la pedina nel punto dato se appartiene al giocatore corrente.
	 * Successivamente calcola le mosse disponibili e le evidenzia nella scacchiera.
	 * @param punto il punto della scacchiera da valutare.
	 */
	private void selezionaPedinaDaMuovere(Point punto) {
		// Se non ho ancora selezionato nessuna pedina
		if (modello.getConfigurazioneCorrente().a(punto) instanceof Pedina) {
			// Se ho cliccato su una delle mie pedine
			Pedina pedina = (Pedina) modello.getConfigurazioneCorrente().a(
					punto);
			if (pedina.colore() == turno) {

				// La seleziono e ottengo le mosse che posso fare
				modello.setMosseDisponibili(new RegoleScacco().verificaMosse(
								modello.getConfigurazioneCorrente(),
								punto)); // ritorna le mosse disponibili
				
				if (modello.getMosseDisponibili().size() > 0) {
					// Se ci sono mosse le evidenzio
					visualizzatore.evidenziaCelle(modello.getMosseDisponibili());
					visualizzatore.evidenziaCella(punto); 
					
					modello.setSelezionato(punto);
					pedinaSelezionata = true;
				}
			}
		}
	}
	
	/**
	 * Se il punto dato corrisponde ad una mossa valida, viene effettuata la mossa.
	 * Se non corrisponde viene deselezionata la pedina.
	 * @param punto il punto da esaminare.
	 */
	private void muovi(Point punto) {
		// Se ho cliccato su una mossa valida
		if (modello.getMosseDisponibili().contains(punto)) {
			Configurazione conf = modello.getConfigurazioneCorrente().creaCopia();

			if (conf.a(modello.getPuntoSelezionato()) instanceof Pedone || conf.a(punto) instanceof Pedina) {
				// Se viene mosso un pedone oppure viene effettuata una mangiata
				numeroMosseSenzaMangiateSenzaPedone = 0;
			} else {
				numeroMosseSenzaMangiateSenzaPedone++;
			}
			
			// Eseguo la mossa
			conf.setPosizione(punto,conf.a(modello.getPuntoSelezionato()));
			conf.setPosizione(modello.getPuntoSelezionato(), new NessunaPedina());

			// Se la mossa riguarda un pedone e questo giunge al lato
			// opposto della scacchiera, viene promosso!
			if ((turno == Modello.BIANCO && punto.x == 0 && conf.a(punto) instanceof Pedone) ||
					(turno == Modello.NERO && punto.x == 7 && conf.a(punto) instanceof Pedone)) {
				recuperaPedina(conf, punto);
			}

			// Memorizzo la nuova configurazione e cambio il turno
			turno = !turno;
			modello.setConfigurazione(conf);

			// Controllo Scacco / ScaccoMatto
			RegoleScacco sc = new RegoleScacco();
			if (sc.isScaccoMatto(conf, turno)) {
				this.partitaFinita();
			} else if (sc.isScacco(conf, !turno)) {
				visualizzatore.scacco(turno);
			}
			
			// Controllo 50 mosse senza movimento pedone o senza mangiate
			if (numeroMosseSenzaMangiateSenzaPedone == 50) {
				this.partitaPatta("50 mosse senza muovere un pedone oppure senza mangiate");
			}
			
			// Controllo se in campo ci sono solo i due re
			if (soloReInCampo(conf)) {
				this.partitaPatta("Sono rimasti solo i Re sulla scacchiera");
			}
		}
		
		pedinaSelezionata = false;		
		visualizzatore.resettaBordiCelle(modello.getMosseDisponibili());
		visualizzatore.resettaBordoCella(modello.getPuntoSelezionato());
	}

	/**
	 * Nel caso in cui un pedone sia giunto all'estremità opposta della scacchiera,
	 * viene richiesto all'utente quale tipo di pedina sostituire al pedone.
	 * @param conf la configurazione in cui inserire la nuova pedina.
	 * @param punto il punto in cui inserire la nuova pedina.
	 */
	private void recuperaPedina(Configurazione conf, Point punto) {
		TipiPedine tipoScelto = visualizzatore.scegliPedinaDaRecuperare();

		// ripristina il tipo
		conf.setPosizione(punto, Pedina.costruisciPedina(tipoScelto, turno));
	}
	
	/**
	 * Verifica se sulla scacchiera sono rimasti solo i due re.
	 * @param conf la configurazione corrente.
	 * @return true se ci sono solo i due re in campo.
	 */
	private boolean soloReInCampo(Configurazione conf) {
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++) {
				Point punto = new Point(i,j);
				if ((conf.a(punto) instanceof Pedina) &&
						!(conf.a(punto) instanceof Re)) {
					return false;
				}
			}
		return true;
	}
	
	@Override
	public void nuovaPartita() {
		fermaTimer();
		inizializzaNuovaPartita();
	}

	@Override
	public void esci() {
		System.exit(0);
	}

	@Override
	public void click(Point punto) {
		if (!partitaFinita) {
			if (pedinaSelezionata) {
				muovi(punto);
			} else {
				selezionaPedinaDaMuovere(punto);
			}
		}
	}
}

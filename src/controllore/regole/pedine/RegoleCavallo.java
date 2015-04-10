package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;

/**
 * Le regole del pezzo Cavallo.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleCavallo extends RegolePedine {

	public RegoleCavallo() {
		super(8); // al massimo 8 mosse possibili
	}
	
	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		// Le 8 mosse del cavallo in senso orario partendo da davanti
		
		Point mossa = new Point(posizione.x - 2, posizione.y + 1);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		mossa = new Point(posizione.x - 1, posizione.y + 2);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}
		
		mossa = new Point(posizione.x + 1, posizione.y + 2);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		mossa = new Point(posizione.x + 2, posizione.y + 1);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		mossa = new Point(posizione.x + 2, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		mossa = new Point(posizione.x + 1, posizione.y - 2);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		mossa = new Point(posizione.x - 1, posizione.y - 2);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}
		
		mossa = new Point(posizione.x - 2, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(mossa) && (RegolePedine.pedinaDiAltroColore(conf, mossa, colore) || RegolePedine.vuota(conf, mossa))) {
			aggiungiMossa(mossa);
		}

		return getMosse();
	}

}

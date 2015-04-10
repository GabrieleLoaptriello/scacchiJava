package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;
import modello.Modello;

/**
 * Le regole del pezzo Pedone.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegolePedone extends RegolePedine {

	public RegolePedone() {
		super(3); // al massimo 3 mosse possibili
	}

	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		// La posizione X successiva dipende dal mio colore
		int prossimaX = colore == Modello.BIANCO ? posizione.x - 1
				: posizione.x + 1;

		// Posso muovermi avanti
		Point avanti = new Point(prossimaX, posizione.y);
		if (RegolePedine.isDentroScacchiera(avanti) && RegolePedine.vuota(conf, avanti)) {
			aggiungiMossa(avanti);
		}

		// Posso mangiare
		Point sx = new Point(prossimaX, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(sx) && RegolePedine.pedinaDiAltroColore(conf, sx, colore))
			aggiungiMossa(sx);
		
		Point dx = new Point(prossimaX, posizione.y + 1);

		if (RegolePedine.isDentroScacchiera(dx) && RegolePedine.pedinaDiAltroColore(conf, dx, colore))
			aggiungiMossa(dx);
		
		return getMosse();
	}

}

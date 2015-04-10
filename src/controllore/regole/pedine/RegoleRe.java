package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;

/**
 * Le regole del pezzo Re
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleRe extends RegolePedine {

	public RegoleRe() {
		super(8); // al massimo 8 mosse possibili
	}
	
	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		// Tutte le posizioni vicine
		Point punto = new Point(posizione.x - 1, posizione.y);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x - 1, posizione.y + 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x , posizione.y + 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x + 1, posizione.y + 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x + 1, posizione.y);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x + 1, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		punto = new Point(posizione.x - 1, posizione.y - 1);
		if (RegolePedine.isDentroScacchiera(punto) && (RegolePedine.pedinaDiAltroColore(conf, punto, colore) || RegolePedine.vuota(conf, punto)))
			aggiungiMossa(punto);
		
		return getMosse();
	}

}

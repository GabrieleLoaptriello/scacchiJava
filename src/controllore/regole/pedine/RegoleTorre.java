package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;


/**
 * Le regole del pezzo Torre.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleTorre extends RegolePedine {

	public RegoleTorre() {
		super(14); // al massimo 14 mosse possibili
	}
	
	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		// Vado in su
		Point punto = new Point(posizione.x - 1, posizione.y);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x - 1, punto.y);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado in giu
		punto = new Point(posizione.x + 1, posizione.y);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x + 1, punto.y);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado a dx
		punto = new Point(posizione.x, posizione.y + 1);
		while(RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado a sx
		punto = new Point(posizione.x, posizione.y - 1);
		while(RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		return getMosse();
	}

}

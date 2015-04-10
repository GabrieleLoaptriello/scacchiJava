package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;
/**
 * Le regole del pezzo Alfiere.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleAlfiere extends RegolePedine {

	public RegoleAlfiere() {
		super(13); // al massimo 13 mosse possibili
	}
	
	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		Point punto;
		
		// Vado in alto a dx
		punto = new Point (posizione.x - 1, posizione.y + 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x - 1, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado in alto a sx
		punto = new Point (posizione.x - 1, posizione.y - 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x - 1, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado in basso a dx
		punto = new Point (posizione.x + 1, posizione.y + 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x + 1, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		// Vado in basso a sx
		punto = new Point (posizione.x + 1, posizione.y - 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point (punto.x + 1, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto) && RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);
		
		return getMosse();
 	}

}

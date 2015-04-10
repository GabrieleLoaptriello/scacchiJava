package controllore.regole.pedine;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;


/**
 * Le regole del pezzo Regina.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleRegina extends RegolePedine {

	public RegoleRegina() {
		super(27); // al massimo 27 mosse possibili
	}

	@Override
	public HashSet<Point> getMosseDisponibili(Configurazione conf,
			Point posizione, boolean colore) {
		// Vado in alto a dx
		Point punto = new Point(posizione.x - 1, posizione.y + 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x - 1, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado in alto a sx
		punto = new Point(posizione.x - 1, posizione.y - 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x - 1, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado in basso a dx
		punto = new Point(posizione.x + 1, posizione.y + 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x + 1, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado in basso a sx
		punto = new Point(posizione.x + 1, posizione.y - 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x + 1, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado in su
		punto = new Point(posizione.x - 1, posizione.y);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x - 1, punto.y);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado in giu
		punto = new Point(posizione.x + 1, posizione.y);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x + 1, punto.y);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado a dx
		punto = new Point(posizione.x, posizione.y + 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x, punto.y + 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		// Vado a sx
		punto = new Point(posizione.x, posizione.y - 1);
		while (RegolePedine.isDentroScacchiera(punto) && RegolePedine.vuota(conf, punto)) {
			aggiungiMossa(punto);
			punto = new Point(punto.x, punto.y - 1);
		}
		if (RegolePedine.isDentroScacchiera(punto)
				&& RegolePedine.pedinaDiAltroColore(conf, punto, colore))
			aggiungiMossa(punto);

		return getMosse();
	}

}

package modello;

import java.awt.Point;

import modello.pedine.Alfiere;
import modello.pedine.Cavallo;
import modello.pedine.ElementoScacchiera;
import modello.pedine.NessunaPedina;
import modello.pedine.Pedone;
import modello.pedine.Re;
import modello.pedine.Regina;
import modello.pedine.Torre;


/**
 * Lo stato della scacchiera implementato su una matrice bidimensionale.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class ConfigurazioneMatrice implements Configurazione {

	private ElementoScacchiera stato[][];

	/**
	 * Crea una copia della configurazione config.
	 * @param config la configurazione da copiare.
	 */
	public ConfigurazioneMatrice(Configurazione config) {
		this.stato = new ElementoScacchiera[8][8];

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				this.stato[i][j] = config.a(new Point(i, j));
	}

	/**
	 * Crea la configurazione iniziale della scacchiera.
	 */
	public ConfigurazioneMatrice() {
		this.stato = new ElementoScacchiera[8][8];
		// x scorre righe da alto a basso
		// y scorre colonne da sx a dx

		/* *******************************
		 * NERI 
		 * ******************************/
		stato[0][0] = new Torre(Modello.NERO);
		stato[0][1] = new Cavallo(Modello.NERO);
		stato[0][2] = new Alfiere(Modello.NERO);
		stato[0][3] = new Regina(Modello.NERO);
		stato[0][4] = new Re(Modello.NERO);
		stato[0][5] = new Alfiere(Modello.NERO);
		stato[0][6] = new Cavallo(Modello.NERO);
		stato[0][7] = new Torre(Modello.NERO);

		for (int i = 0; i < 8; i++)
			stato[1][i] = new Pedone(Modello.NERO);

		/* *******************************
		 * PARTE CENTRALE 
		 * ******************************/
		for (int i = 2; i < 6; i++)
			for (int j = 0; j < 8; j++)
				stato[i][j] = new NessunaPedina();

		/* *******************************
		 * BIANCHI 
		 * ******************************/
		stato[7][0] = new Torre(Modello.BIANCO);
		stato[7][1] = new Cavallo(Modello.BIANCO);
		stato[7][2] = new Alfiere(Modello.BIANCO);
		stato[7][3] = new Regina(Modello.BIANCO);
		stato[7][4] = new Re(Modello.BIANCO);
		stato[7][5] = new Alfiere(Modello.BIANCO);
		stato[7][6] = new Cavallo(Modello.BIANCO);
		stato[7][7] = new Torre(Modello.BIANCO);

		for (int i = 0; i < 8; i++)
			stato[6][i] = new Pedone(Modello.BIANCO);
	}

	@Override
	public ElementoScacchiera a(Point punto) {
		return stato[punto.x][punto.y];
	}

	@Override
	public void setPosizione(Point punto, ElementoScacchiera valore) {
		stato[punto.x][punto.y] = valore;
	}
	
	@Override
	public Configurazione creaCopia() {
		return new ConfigurazioneMatrice(this);
	}
}

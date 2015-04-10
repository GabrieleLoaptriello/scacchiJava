package modello.pedine;

import modello.Modello;


/**
 * Una generica pedina sulla scacchiera.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public abstract class Pedina extends ElementoScacchiera {

	private boolean colore;

	public Pedina(boolean colore) {
		this.colore = colore;
	}

	/**
	 * @return il colore della pedina.
	 */
	public boolean colore() {
		return colore;
	}
	
	/**
	 * Costruisce la pedina del tipo e colore specificato.
	 * @param tipo il tipo della pedina.
	 * @param colore il colore della pedina.
	 * @return la pedina.
	 */
	public static Pedina costruisciPedina(TipiPedine tipo, boolean colore) {
		switch (tipo) {
		case ALFIERE:
			return new Alfiere(colore);
		case CAVALLO:
			return new Cavallo(colore);
		case PEDONE:
			return new Pedone(colore);
		case RE:
			return new Re(colore);
		case REGINA:
			return new Regina(colore);
		case TORRE:
			return new Torre(colore);
		default:
			return null;
		}
	}

	/**
	 * @return il tipo della pedina.
	 */
	public abstract TipiPedine getTipo();
	
	public String toString(){
		return getClass().getSimpleName() + " " + (colore == Modello.BIANCO ? "bianco" : "nero");
	}
	
}

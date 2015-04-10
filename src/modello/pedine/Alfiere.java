package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Alfiere.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Alfiere extends Pedina {

	public Alfiere(boolean colore) {
		super(colore);
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Alfiere) && ((Alfiere)obj).colore() == this.colore();
	}

	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/alfiere_"+ (super.colore() == Modello.BIANCO ? "bianco" : "nero") +".png"));
	}
	
	@Override
	public TipiPedine getTipo() {
		return TipiPedine.ALFIERE;
	}

}

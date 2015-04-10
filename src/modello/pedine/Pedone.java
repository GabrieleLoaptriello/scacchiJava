package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Pedone.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Pedone extends Pedina {

	public Pedone(boolean colore) {
		super(colore);
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Pedone) && ((Pedone)obj).colore() == this.colore();
	}

	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/pedone_" + (super.colore() == Modello.BIANCO ? "bianco" : "nero") + ".png"));
	}

	@Override
	public TipiPedine getTipo() {
		return TipiPedine.PEDONE;
	}
}

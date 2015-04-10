package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Cavallo.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Cavallo extends Pedina {

	public Cavallo(boolean colore) {
		super(colore);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Cavallo) && ((Cavallo)obj).colore() == this.colore();
	}
	
	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/cavallo_"+ (super.colore() == Modello.BIANCO ? "bianco" : "nero") +".png"));
	}

	@Override
	public TipiPedine getTipo() {
		return TipiPedine.CAVALLO;
	}
}

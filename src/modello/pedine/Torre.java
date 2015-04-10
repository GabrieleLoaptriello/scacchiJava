package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Torre.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Torre extends Pedina {

	public Torre(boolean colore) {
		super(colore);
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Torre) && ((Torre)obj).colore() == this.colore();
	}

	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/torre_"+ (super.colore() == Modello.BIANCO ? "bianco" : "nero") +".png"));
	}

	@Override
	public TipiPedine getTipo() {
		return TipiPedine.TORRE;
	}
}

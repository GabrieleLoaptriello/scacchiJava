package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Re.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Re extends Pedina {

	public Re(boolean colore) {
		super(colore);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Re) && ((Re)obj).colore() == this.colore();
	}
	
	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/re_"+ (super.colore() == Modello.BIANCO ? "bianco" : "nero") +".png"));
	}

	@Override
	public TipiPedine getTipo() {
		return TipiPedine.RE;
	}
}

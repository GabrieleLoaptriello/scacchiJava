package modello.pedine;

import javax.swing.ImageIcon;

import modello.Modello;


/**
 * Il pezzo Regina.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class Regina extends Pedina {

	public Regina(boolean colore) {
		super(colore);
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Regina) && ((Regina)obj).colore() == this.colore();
	}

	@Override
	public ImageIcon getImage() {
		return new ImageIcon(getClass().getResource("/modello/pedine/immagini/regina_"+ (super.colore() == Modello.BIANCO ? "bianco" : "nero") +".png"));
	}
	
	@Override
	public TipiPedine getTipo() {
		return TipiPedine.REGINA;
	}
}

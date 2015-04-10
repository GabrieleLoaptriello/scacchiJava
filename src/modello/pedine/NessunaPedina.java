package modello.pedine;

import javax.swing.ImageIcon;


/**
 * Una cella vuota sulla scacchiera.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class NessunaPedina extends ElementoScacchiera {

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof NessunaPedina);
	}
	
	@Override
	public ImageIcon getImage() {
		return null;
	}

}

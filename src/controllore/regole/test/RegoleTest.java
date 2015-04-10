package controllore.regole.test;

import java.awt.Point;
import java.util.HashSet;

import modello.Configurazione;
import modello.ConfigurazioneMatrice;
import modello.Modello;
import modello.pedine.Cavallo;
import modello.pedine.NessunaPedina;
import modello.pedine.Pedone;
import modello.pedine.Re;
import modello.pedine.Regina;

import org.junit.Assert;
import org.junit.Test;

import controllore.regole.scacco.RegoleScacco;


/**
 * Test su mosse delle pedine e su scacco matto.
 * @author Marco Panato - Nicola Dall'Ora
 * 
 */
public class RegoleTest {

	/**
	 * Testo lo scacco matto con lo scacco dell'imbecille.
	 */
	@Test
	public void scaccoMatto(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(0, 3),new NessunaPedina());
		conf.setPosizione(new Point(1, 4),new NessunaPedina());
		conf.setPosizione(new Point(3, 4),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(4, 6),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(4, 7),new Regina(Modello.NERO));
		conf.setPosizione(new Point(5, 5),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(6, 5),new NessunaPedina());
		conf.setPosizione(new Point(6, 6),new NessunaPedina());
		
		Assert.assertTrue(new RegoleScacco().isScaccoMatto(conf, Modello.BIANCO));
	}
	
	/**
	 * Testo lo scacco in una configurazione.
	 */
	@Test
	public void scacco(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(1, 3),new NessunaPedina());
		conf.setPosizione(new Point(2, 3),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(4, 0),new Regina(Modello.BIANCO));
		conf.setPosizione(new Point(5, 2),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(6, 2),new NessunaPedina());
		conf.setPosizione(new Point(7, 3),new NessunaPedina());
		
		// Sono in scacco
		Assert.assertTrue(new RegoleScacco().isScacco(conf, Modello.BIANCO));
		// Il pedone può muovere perché rimuove lo scacco
		Assert.assertTrue(new RegoleScacco().verificaMosse(conf, new Point(1,2)).contains(new Point(2,2)));
		// Il re non può muoversi perché in tutti i punti sarebbe ancora in scacco
		Assert.assertEquals(0, new RegoleScacco().verificaMosse(conf, new Point(0,4)).size());
	}
	
	/**
	 * Testo le mosse del pedone in una certa configurazione.
	 */
	@Test
	public void mossePedone(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(1, 4),new NessunaPedina());
		conf.setPosizione(new Point(3, 4),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(4, 5),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(6, 5),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(4,5));
		
		Assert.assertEquals(2, mosse.size()); // avanti + mangiata sx
		Assert.assertTrue(mosse.contains(new Point(3,5)));
		Assert.assertTrue(mosse.contains(new Point(3,4)));
	}
	
	/**
	 * Testo le mosse del cavallo in una certa configurazione.
	 */
	@Test
	public void mosseCavallo(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(1, 3),new NessunaPedina());
		conf.setPosizione(new Point(1, 4),new NessunaPedina());
		conf.setPosizione(new Point(2, 3),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(2, 4),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(3, 3),new Cavallo(Modello.BIANCO));
		conf.setPosizione(new Point(7, 1),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(3,3));
		
		Assert.assertEquals(8, mosse.size());
		Assert.assertTrue(mosse.contains(new Point(2,1)));
		Assert.assertTrue(mosse.contains(new Point(1,2))); // mangio il pedone
		Assert.assertTrue(mosse.contains(new Point(1,4)));
		Assert.assertTrue(mosse.contains(new Point(2,5)));
		Assert.assertTrue(mosse.contains(new Point(4,1)));
		Assert.assertTrue(mosse.contains(new Point(4,5)));
		Assert.assertTrue(mosse.contains(new Point(5,2)));
		Assert.assertTrue(mosse.contains(new Point(5,4)));
	}
	
	/**
	 * Testo le mosse della torre in una certa configurazione.
	 */
	@Test
	public void mosseTorre(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(6, 7),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(7,7));
		
		Assert.assertEquals(6, mosse.size()); // avanti
		for(int i=6; i>0; i--){
			Assert.assertTrue(mosse.contains(new Point(i,7)));
		}
	}
	
	/**
	 * Testo le mosse dell'alfiere in una certa configurazione.
	 */
	@Test
	public void mosseAlfiere(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(1, 0),new NessunaPedina());
		conf.setPosizione(new Point(2, 0),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(5, 4),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(6, 4),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(7,5));
		
		Assert.assertEquals(5, mosse.size());
		Assert.assertTrue(mosse.contains(new Point(6,4)));
		Assert.assertTrue(mosse.contains(new Point(5,3)));
		Assert.assertTrue(mosse.contains(new Point(4,2)));
		Assert.assertTrue(mosse.contains(new Point(3,1)));
		Assert.assertTrue(mosse.contains(new Point(2,0))); // mangio il pedone
	}
	
	/**
	 * Testo le mosse della regina in una certa configurazione.
	 */
	@Test
	public void mosseRegina(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(1, 3),new NessunaPedina());
		conf.setPosizione(new Point(7, 3),new NessunaPedina());
		conf.setPosizione(new Point(2, 3),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(5, 4),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(4, 6),new Regina(Modello.BIANCO));
		conf.setPosizione(new Point(6, 4),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(4,6));
		
		Assert.assertEquals(20, mosse.size());
	}
	
	/**
	 * Testo le mosse del re in una certa configurazione.
	 */
	@Test
	public void mosseRe(){
		Configurazione conf = new ConfigurazioneMatrice();
		conf.setPosizione(new Point(7, 4),new NessunaPedina());
		conf.setPosizione(new Point(6, 3),new NessunaPedina());
		conf.setPosizione(new Point(5, 3),new Pedone(Modello.BIANCO));
		conf.setPosizione(new Point(4, 4),new Re(Modello.BIANCO));
		conf.setPosizione(new Point(2, 0),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(2, 4),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(2, 6),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(2, 7),new Pedone(Modello.NERO));
		conf.setPosizione(new Point(1, 0),new NessunaPedina());
		conf.setPosizione(new Point(1, 4),new NessunaPedina());
		conf.setPosizione(new Point(1, 6),new NessunaPedina());
		conf.setPosizione(new Point(1, 7),new NessunaPedina());
		
		HashSet<Point> mosse = new RegoleScacco().verificaMosse(conf, new Point(4,4));
		
		Assert.assertEquals(5, mosse.size());
		Assert.assertTrue(mosse.contains(new Point(3,4)));
		Assert.assertTrue(mosse.contains(new Point(4,3)));
		Assert.assertTrue(mosse.contains(new Point(4,5)));
		Assert.assertTrue(mosse.contains(new Point(5,4)));
		Assert.assertTrue(mosse.contains(new Point(5,5)));
		Assert.assertFalse(mosse.contains(new Point(3,3))); // mossa che causerebbe uno scacco
		Assert.assertFalse(mosse.contains(new Point(3,5))); // mossa che causerebbe uno scacco
		Assert.assertFalse(mosse.contains(new Point(5,3))); // c'è una mia pedina
	}
}

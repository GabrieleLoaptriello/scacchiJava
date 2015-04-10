package visualizzatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import modello.Configurazione;
import modello.Modello;
import modello.ModelloScacchi;
import modello.pedine.TipiPedine;
import controllore.Controllore;
import controllore.ControlloreScacchi;


/**
 * L'interfaccia Grafica dell'applicazione.
 * @author Marco Panato VR379887 - Nicola Dall'Ora VR380496
 * 
 */
public class Grafica extends JFrame implements Visualizzatore {

	private static final long serialVersionUID = -2358501133469058280L;

	/**
	 * Colore di sfondo chiaro della scacchiera.
	 */
	final private static Color CHIARO = new Color(255, 228, 181);
	
	/**
	 * Colore di sfondo scuro della scacchiera.
	 */
	final private static Color SCURO = new Color(205, 133, 63);
	
	/**
	 * Colore del bordo di default della scacchiera.
	 */
	final private static LineBorder BORDO_GRIGIO = new LineBorder(Color.GRAY);
	
	/**
	 * Colore e dimensione del bordo applicato quando seleziono le mosse disponibili.
	 */
	final private static LineBorder BORDO_GIALLO = new LineBorder(Color.YELLOW, 5);

	private Modello modello;
	private Controllore controllore;

	/**
	 * Celle della scacchiera.
	 */
	private JButton[][] pulsanti;
	
	/**
	 * Indica il verificarsi di uno scacco per il giocatore bianco/nero.
	 */
	private JLabel lScacco;
	
	/**
	 * Indica il turno attuale e il tempo totale utilizzato dal giocatore corrente.
	 */
	private JLabel lInfo;

	public Grafica() {
		super("Scacchi");
		costruisciGrafica();

		modello = new ModelloScacchi(this);
		controllore = new ControlloreScacchi(this);
	}

	/**
	 * Chiama i metodi che impostano i pannelli che compongono il JFrame principale.
	 */
	private void costruisciGrafica() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		impostaMenu();
		
		impostaPannelloScacchi();
		
		impostaPannelloSud();
		
		pack();
		mettiAlCentro();
	}

	/**
	 * Imposta la JMenuBar del JFrame principale.
	 */
	private void impostaMenu(){
		JMenuBar barra = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem nuovaPartita = new JMenuItem("Nuova partita");
		nuovaPartita.addActionListener(event -> {
			if (JOptionPane.showConfirmDialog(rootPane,
					"Vuoi iniziare una nuova partita?", null,
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				controllore.nuovaPartita();
			}
		});
		JMenuItem esci = new JMenuItem("Esci");
		esci.addActionListener(event -> {
			if (JOptionPane.showConfirmDialog(rootPane, "Vuoi uscire?", null,
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				controllore.esci();
			}
		});
		file.add(nuovaPartita);
		file.add(esci);
		barra.add(file);

		this.setJMenuBar(barra);
	}
	
	/**
	 * Imposta il pannello centrale. 
	 */
	private void impostaPannelloScacchi() {
		JPanel pannelloScacchi = new JPanel(new GridLayout(8, 8));
		boolean chiaro;
		pulsanti = new JButton[8][8];

		for (int i = 0; i < 8; i++) {
			chiaro = i % 2 == 0;

			for (int j = 0; j < 8; j++) {
				pulsanti[i][j] = new JButton();
				pulsanti[i][j].setBackground(chiaro ? CHIARO : SCURO);
				pulsanti[i][j].setFocusable(false);
				chiaro = !chiaro;
				pannelloScacchi.add(pulsanti[i][j]);
				pulsanti[i][j].addActionListener(new BottoniActionListener(new Point(i,j)));
			}
		}
		getContentPane().add(pannelloScacchi, BorderLayout.CENTER);
	}
	
	/**
	 * Imposta il pannello informativo.
	 */
	private void impostaPannelloSud() {
		JPanel pannelloSud = new JPanel(new GridLayout(1, 3));
		
		pannelloSud.add(new JLabel());
		
		lScacco = new JLabel();
		lScacco.setHorizontalAlignment(SwingConstants.CENTER);
		pannelloSud.add(lScacco);
		
		lInfo = new JLabel();
		lInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		pannelloSud.add(lInfo);
		
		this.getContentPane().add(pannelloSud, BorderLayout.SOUTH);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(550, 550);
	}

	/**
	 * Visualizzo l'applicazione al centro dello schermo.
	 */
	private void mettiAlCentro() {
		Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension d = getPreferredSize();
		setLocation((dimensioneSchermo.width / 2) - d.width/2 , (dimensioneSchermo.height / 2) - d.height/2);
	}

	public static void main(String[] args) {
		impostaLookAndFeel();
		Grafica g = new Grafica();
		g.setVisible(true);
	}

	/**
	 * Imposta il tema di default per l'applicazione.
	 */
	private static void impostaLookAndFeel() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
		}
	}

	/* **********************************************************************
	 * Metodi interfaccia
	 * *********************************************************************/

	@Override
	public Modello getModello() {
		return modello;
	}

	@Override
	public void setControllore(Controllore c) {
		this.controllore = c;
	}

	@Override
	public void modelloCambiato() {
		Configurazione conf = modello.getConfigurazioneCorrente();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ImageIcon ic = conf.a(new Point(i, j)).getImage();
				this.pulsanti[i][j].setIcon(ic);
			}
		}
	}
	
	@Override
	public void partitaFinita(boolean quale, int tempoBianchi, int tempoNeri) {
		JOptionPane.showMessageDialog(rootPane,String.format("I %s vincono!\nTempo totale bianchi: %02d:%02d\nTempo totale neri: %02d:%02d",
					quale == Modello.BIANCO ? "bianchi" : "neri",
					tempoBianchi/60, tempoBianchi%60,
					tempoNeri/60, tempoNeri%60));
	}
	
	@Override
	public void partitaPatta(String causa, int tempoBianchi, int tempoNeri) {
		JOptionPane.showMessageDialog(rootPane, String.format("Partita finita in pareggio!\nCausa: %s\nTempo totale bianchi: %02d:%02d\n" +
					"Tempo totale neri: %02d:%02d",
					causa,
					tempoBianchi/60, tempoBianchi%60,
					tempoNeri/60, tempoNeri%60));
	}
	
	@Override
	public void scacco(boolean quale) {
		lScacco.setText(String.format("%s SOTTO SCACCO!",quale == Modello.BIANCO ? "BIANCHI" : "NERI"));
	}

	@Override
	public void evidenziaCelle(HashSet<Point> punti) {
		for (Point p : punti) {
			this.pulsanti[p.x][p.y].setBorder(BORDO_GIALLO);
		}
	}
	
	@Override
	public void evidenziaCella(Point punto) {
		this.pulsanti[punto.x][punto.y].setBorder(BORDO_GIALLO);
	}
	
	@Override
	public void resettaBordiCelle() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pulsanti[i][j].setBorder(BORDO_GRIGIO);
			}
		}
	}

	@Override
	public void resettaBordiCelle(HashSet<Point> punti) {
		for (Point p: punti) {
			pulsanti[p.x][p.y].setBorder(BORDO_GRIGIO);
		}
	}
	
	@Override
	public void resettaBordoCella(Point punto) {
		pulsanti[punto.x][punto.y].setBorder(BORDO_GRIGIO);
	}

	@Override
	public TipiPedine scegliPedinaDaRecuperare() {
		JPanel pannello = new JPanel(new GridLayout(4, 1));
		ButtonGroup gruppoBottoni = new ButtonGroup();
		JRadioButton pulsanti[] = new JRadioButton[4];

		// Bottoni da visualizzare
		pulsanti[0] = new JRadioButton("Torre");
		pulsanti[1] = new JRadioButton("Cavallo");
		pulsanti[2] = new JRadioButton("Alfiere");
		pulsanti[3] = new JRadioButton("Regina");
		gruppoBottoni.add(pulsanti[0]);
		gruppoBottoni.add(pulsanti[1]);
		gruppoBottoni.add(pulsanti[2]);
		gruppoBottoni.add(pulsanti[3]);
		pannello.add(pulsanti[0]);
		pannello.add(pulsanti[1]);
		pannello.add(pulsanti[2]);
		pannello.add(pulsanti[3]);


		// Mostra finestra di dialogo finché non viene effettuata la scelta
		do {
			JOptionPane.showOptionDialog(rootPane, pannello, "Fai la tua scelta",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, null, null);
			for (int i = 0; i < 4; i++)
				if (pulsanti[i].isSelected())
					return TipiPedine.values()[i];
		} while (true);
	}
	
	@Override
	public void modificaInformazioni(String info) {
		lInfo.setText(info);
	}

	@Override
	public boolean richiediNuovaPartita() {
		return JOptionPane.showConfirmDialog(rootPane, "Vuoi iniziare una nuova partita?") == JOptionPane.YES_OPTION;
	}
	
	/**
	 * ActionListener di ogni pulsante della scacchiera.
	 */
	private class BottoniActionListener implements ActionListener {

		private Point punto;
		
		public BottoniActionListener(Point punto) {
			this.punto = punto;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lScacco.setText("");
			controllore.click(punto);
		}
		
	}
}

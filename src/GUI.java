import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private Image icona;
	private JLabel testo = new JLabel();
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon sfondo = new ImageIcon("res/etc/sfondo.jpg");
	ImageIcon sfondo2 = new ImageIcon("res/etc/sfondo2.jpg");
	private ImageIcon sfondo3 = new ImageIcon("res/etc/sfondo3.jpg");
	private JButton inizia = new JButton(new ImageIcon("res/etc/inizia.png"));
	private JButton pannello = new JButton(new ImageIcon("res/etc/pannello.png"));
	private AudioInputStream input;
	private Clip c;
	private AudioInputStream input2;
	private Clip c2;
	private TimerTask carica;
	private Timer t = new Timer();
	private Vector<String> domande = new Vector<String>();
	private Vector<Vector<String>> risposte = new Vector<Vector<String>>();
	private Vector<Integer> corrette = new Vector<Integer>();
	private Vector<Integer> daLasciare = new Vector<Integer>();
	private int cont;
	Domanda domanda;
	public GUI() {
		creaSuono("res/sounds/menu.wav", true);
		icona = Toolkit.getDefaultToolkit().getImage("res/etc/logo.png");
		setIconImage(icona);
		// Impostazione del layout adatto per lo sfondo
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		testo.setBounds(0, 490, sfondo.getIconWidth(), 90);
		testo.setForeground(Color.white);
		testo.setFont(testo.getFont().deriveFont(30f));
		testo.setHorizontalAlignment(SwingConstants.CENTER);
		testo.setVisible(false);
		add(testo);
		// La finestra ha le dimensioni della larghezza e dell'altezza
		// dell'immagine di sfondo.
		setBounds(0, 0, sfondo.getIconWidth(), sfondo.getIconHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2));
		setTitle("Chi vuol essere milionario - History Edition");
		pack();
		inizia.setBounds(304, 486, 292, 94);
		inizia.setBackground(Color.black);
		inizia.setBorder(null);
		inizia.setOpaque(false);
		inizia.setFocusable(false);
		inizia.addActionListener(this);
		inizia.setContentAreaFilled(false);
		inizia.setCursor(cur);
		add(inizia);
		pannello.setBounds(253, 16, 394, 94);
		pannello.setBackground(Color.black);
		pannello.setBorder(null);
		pannello.setOpaque(false);
		pannello.setFocusable(false);
		pannello.addActionListener(this);
		pannello.setContentAreaFilled(false);
		pannello.setCursor(cur);
		add(pannello);
		cont = 0;
		if (domande.size() == 0) {
			inizia.setVisible(false);
			testo.setText("<html><body align='center'>Nessuna domanda inserita.<br>Utilizza il pannello di controllo "
					+ "per aggiungere delle domande.</body></html>");
			testo.setVisible(true);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(inizia)) {
				rimuoviSuono();
				inizia.setVisible(false);
				testo.setText("Caricamento...");
				testo.setVisible(true);
				carica = new Carica(this);
				t.schedule(carica, 0);
		}
		if (arg0.getSource().equals(pannello)) {
			try {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","cd QCP && java -jar pannello.jar");
				Process process = builder.start();			
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	public void nuovaDomanda() {
		rimuoviSuono();
		if (cont >= 0 && cont < 3) {
			creaSuono("res/sounds/question_easy.wav", true);
		} else {
			if (cont < 8) {
				creaSuono("res/sounds/question_medium.wav", true);
			} else {
				creaSuono("res/sounds/question_hard.wav", true);
			}
		}
		if (cont < domande.size()) {
			domanda.visualizzaDomanda(domande.elementAt(cont), risposte.elementAt(cont), corrette.elementAt(cont), daLasciare.elementAt(cont), cont);
			cont++;
		} else {
			rimuoviSuono();
			creaSuono("res/sounds/menu.wav", true);
			this.setContentPane(new JLabel(sfondo3));
		}
		repaint();
		revalidate();
	}
	public int getCont() {
		return cont;
	}
	public Cursor getC() {
		return cur;
	}
	
	public void creaSuono(String nome, boolean ripeti) {
		try {
	        input = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        c = AudioSystem.getClip();
	        c.open(input);
	    } catch(Exception ex) {
		ex.printStackTrace();
	    }
		c.start();
		if (ripeti) {
			c.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void rimuoviSuono() {
		c.stop();
	}
	
	public void creaSuono2(String nome, boolean ripeti) {
		try {
	        input2 = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        c2 = AudioSystem.getClip();
	        c2.open(input2);
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		c2.start();
		if (ripeti) {
			c2.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void rimuoviSuono2() {
		if (c2 != null) {
			c2.stop();
		}
	}
	
	public void impostaDomanda(String domanda, Vector<String> risposte, int corretta, int daLasciare) {
		domande.add(domanda);
		this.risposte.add(risposte);
		corrette.add(corretta);
		this.daLasciare.add(daLasciare);
	}
}

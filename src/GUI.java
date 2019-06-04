import java.awt.event.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private Image icona;
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon sfondo = new ImageIcon("res/etc/sfondo.jpg");
	ImageIcon sfondo2 = new ImageIcon("res/etc/sfondo2.jpg");
	private ImageIcon sfondo3 = new ImageIcon("res/etc/sfondo3.jpg");
	private JButton inizia = new JButton(new ImageIcon("res/etc/inizia.png"));
	private AudioInputStream input;
	private Clip c;
	private AudioInputStream input2;
	private Clip c2;
	private TimerTask carica;
	private Timer t = new Timer();
	private String[] domande = {"", 
			"Attorno a quali secoli ebbe inizio la Prima Rivoluzione Industriale?", 
			"Chi scoprì il coke, combustibile ottenuto dalla distillazione del carbon fossile?", 
			"Quale dei seguenti paesi è stato l'ultimo ad industrializzarsi, e quando?",
			"Chi sostenne, assieme ad Adam Smith, la teoria del liberismo?",
			"Quale tra queste malattie NON si diffuse con la nascita delle città industrializzate?",
			"In quale anno il lavoro minorile è divenuto vietato per legge, in Italia?",
			"Quanti capi di Stato parteciparono al Congresso di Vienna?",
			"Dopo il Congresso di Vienna, la Francia divenne:",
			"Quanti moti insurrezionali scoppiarono in Europa tra il 1820 e il 1821?",
			"Quali sono le tre componenti fondamentali di uno Stato?"};
			
	private String[][] risposte = {{},
			{"XV-XVI secolo", "XVI-XVII secolo", "XVII-XVIII secolo", "XVIII-XIX secolo"},
			{"John Kay", "James Hargreaves", "Abraham Darby", "James Watt"},
			{"India, 1940", "Cina, 1952", "India, 1875", "Cina, 1840"},
			{"Friedrich List", "Thomas Hobbes", "David Ricardo", "Jean-Jacques Rousseau"},
			{"Peste", "Tifo", "Bronchite", "Tubercolosi"},
			{"1942", "1958", "1967", "1974"},
			{"Trecento", "Quattrocento", "Cinquecento", "Seicento"},
			{"Una repubblica parlamentare", "Una monarchia costituzionale", "Una monarchia parlamentare", "Una monarchia assoluta"},
			{"Quattro", "Cinque", "Sei", "Sette"},
			{"Economia, lavoro, sanità", "Infrastrutture, popolo, società", "Popolo, sovranità, territorio", "Turismo, territorio, istruzione"}};
	private int[] corrette = {0, 3, 2, 1, 2, 0, 2, 1, 1, 3, 2};
	private int[] daLasciare = {0, 2, 0, 0, 1, 2, 1, 3, 3, 1, 0};
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
		cont = 1;
	}
	public void actionPerformed(ActionEvent arg0) {
		rimuoviSuono();
		if (arg0.getSource().equals(inizia)) {
			carica = new Carica(this);
			t.schedule(carica, 0);
			
		}
	}
	
	public void nuovaDomanda() {
		rimuoviSuono();
		if (cont >= 1 && cont < 4) {
			creaSuono("res/sounds/question_easy.wav", true);
		} else {
			if (cont < 8) {
				creaSuono("res/sounds/question_medium.wav", true);
			} else {
				creaSuono("res/sounds/question_hard.wav", true);
			}
		}
		if (cont <= 10) {
			domanda.visualizzaDomanda(domande[cont], risposte[cont], corrette[cont], daLasciare[cont], cont);
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
	
}

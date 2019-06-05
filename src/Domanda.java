import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
@SuppressWarnings("serial")
public class Domanda extends Container implements ActionListener {
	private GUI g;
	private JButton[] risposte = new JButton[4];
	private JLabel info = new JLabel();
	private JButton avanti = new JButton(new ImageIcon("res/etc/avanti.png"));
	private ImageIcon normale = new ImageIcon("res/etc/risposta.png");
	private ImageIcon corretta = new ImageIcon("res/etc/rispostaCor.png");
	private ImageIcon sbagliata = new ImageIcon("res/etc/rispostaSba.png");
	private ImageIcon cliccata = new ImageIcon("res/etc/rispostaClick.png");
	private JButton aiuto50 = new JButton(new ImageIcon("res/etc/50.50.png"));
	private JButton aiutoPubblico = new JButton(new ImageIcon("res/etc/pubblico.png"));
	private JLabel nDomanda;
	private JLabel domanda;
	private JLabel risposta1Txt;
	private JLabel risposta2Txt;
	private JLabel risposta3Txt;
	private JLabel risposta4Txt;
	private int rispostaCorretta;
	private int rispostaCliccata;
	private int daLasciare;
	private boolean verificato;
	private boolean usato50;
	private boolean usatoPubblico;
	public Domanda(GUI g) {
		int i;
		this.g = g;
		this.domanda = new JLabel();
		this.domanda.setBounds(109, 327, 693, 76);
		this.domanda.setHorizontalAlignment(SwingConstants.CENTER);
		this.domanda.setForeground(Color.white);
		this.domanda.setFont(this.domanda.getFont().deriveFont(24f));
		add(this.domanda);
		nDomanda = new JLabel();
		nDomanda.setBounds(679, 63, 165, 32);
		nDomanda.setHorizontalAlignment(SwingConstants.CENTER);
		nDomanda.setFont(this.domanda.getFont());
		nDomanda.setForeground(Color.white);
		add(nDomanda);
		info.setBounds(17, 0, 640, 342);
		info.setVisible(false);
		add(info);
		for(i=0; i<4; i++) {
			this.risposte[i] = new JButton(normale);
			this.risposte[i].setBackground(Color.black);
			this.risposte[i].setBorder(null);
			this.risposte[i].setOpaque(false);
			this.risposte[i].setFocusable(false);
			this.risposte[i].addActionListener(this);
			this.risposte[i].setContentAreaFilled(false);
			this.risposte[i].setCursor(g.getC());
			add(this.risposte[i]);
		}
		avanti.setBounds(772, 250, 62, 62);
		avanti.setBackground(Color.black);
		avanti.setBorder(null);
		avanti.setOpaque(false);
		avanti.setFocusable(false);
		avanti.addActionListener(this);
		avanti.setContentAreaFilled(false);
		avanti.setCursor(g.getC());
		avanti.setVisible(false);
		add(avanti);
		aiuto50.setBounds(677, 121, 81, 62);
		aiuto50.setBackground(Color.black);
		aiuto50.setBorder(null);
		aiuto50.setOpaque(false);
		aiuto50.setFocusable(false);
		aiuto50.addActionListener(this);
		aiuto50.setContentAreaFilled(false);
		aiuto50.setCursor(g.getC());
		add(aiuto50);
		aiutoPubblico.setBounds(767, 121, 81, 62);
		aiutoPubblico.setBackground(Color.black);
		aiutoPubblico.setBorder(null);
		aiutoPubblico.setOpaque(false);
		aiutoPubblico.setFocusable(false);
		aiutoPubblico.addActionListener(this);
		aiutoPubblico.setContentAreaFilled(false);
		aiutoPubblico.setCursor(g.getC());
		add(aiutoPubblico);
		this.risposte[0].setBounds(13, 425, 420, 61);
		this.risposte[1].setBounds(459, 425, 420, 61);
		this.risposte[2].setBounds(13, 493, 420, 61);
		this.risposte[3].setBounds(459, 493, 420, 61);
		risposta1Txt = new JLabel();
		risposta1Txt.setForeground(Color.white);
		risposta1Txt.setFont(this.domanda.getFont().deriveFont(19f));
		this.risposte[0].add(risposta1Txt);
		risposta2Txt = new JLabel("        B. "+risposte[1]);
		risposta2Txt = new JLabel();
		risposta2Txt.setForeground(Color.white);
		risposta2Txt.setFont(this.domanda.getFont().deriveFont(19f));
		this.risposte[1].add(risposta2Txt);
		risposta3Txt = new JLabel("        C. "+risposte[2]);
		risposta3Txt = new JLabel();
		risposta3Txt.setForeground(Color.white);
		risposta3Txt.setFont(this.domanda.getFont().deriveFont(19f));
		this.risposte[2].add(risposta3Txt);
		risposta4Txt = new JLabel("        D. "+risposte[3]);
		risposta4Txt = new JLabel();
		risposta4Txt.setForeground(Color.white);
		risposta4Txt.setFont(this.domanda.getFont().deriveFont(19f));
		this.risposte[3].add(risposta4Txt);
		usato50 = false;
		usatoPubblico = false;
	}

	public void visualizzaDomanda(String domanda, Vector<String> risposte, int corretta, int daLasciare, int cont) {
		info.setVisible(false);
		info.setIcon(new ImageIcon("res/info/info"+(cont+1)+".png"));
		nDomanda.setText("Domanda n."+cont);
		verificato = false;
		avanti.setVisible(false);
		this.domanda.setText("<html><body align='center'>"+domanda+"</body></html>");
		risposta1Txt.setText("        A. "+risposte.elementAt(0));
		risposta2Txt.setText("        B. "+risposte.elementAt(1));
		risposta3Txt.setText("        C. "+risposte.elementAt(2));
		risposta4Txt.setText("        D. "+risposte.elementAt(3));
		for(int i=0; i<4; i++) {
			this.risposte[i].setEnabled(true);
			this.risposte[i].setVisible(true);
			this.risposte[i].setIcon(normale);
			this.risposte[i].addActionListener(this);
			this.risposte[i].setCursor(g.getC());
		}
		this.rispostaCorretta = corretta;
		this.daLasciare = daLasciare;
	}
	
	public void actionPerformed(ActionEvent e) {
		int i;
		for (i=0; i<4; i++) {
			if (e.getSource().equals(risposte[i]) && !verificato) {
				g.rimuoviSuono();
				g.rimuoviSuono2();
				g.creaSuono("res/sounds/selezionata.wav", false);
				rispostaCliccata = i;
				risposte[i].setIcon(cliccata);
				risposte[i].removeActionListener(this);
				risposte[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				avanti.setVisible(true);
				for (i=0; i<4; i++) {
					if (i!= rispostaCliccata) {
						risposte[i].setEnabled(false);
					}
				}
			}
		}
		if (e.getSource().equals(aiuto50) && !info.isVisible() && !avanti.isVisible() && !usato50) {
			g.creaSuono2("res/sounds/5050.wav", false);
			aiuto50.setIcon(new ImageIcon("res/etc/aiuto50Usato.png"));
			aiuto50.removeActionListener(this);
			aiuto50.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			for (i=0; i<4; i++) {
				if (i != rispostaCorretta && i != daLasciare) {
					this.risposte[i].setVisible(false);
				}
			}
			usato50 = true;
		}
		if (e.getSource().equals(aiutoPubblico) && !info.isVisible() && !avanti.isVisible() && !usatoPubblico) {
			g.rimuoviSuono();
			g.creaSuono("res/sounds/pubblico.wav", true);
			aiutoPubblico.setIcon(new ImageIcon("res/etc/aiutoPubblicoUsato.png"));
			aiutoPubblico.removeActionListener(this);
			aiutoPubblico.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			usatoPubblico = true;
		}
		if (e.getSource().equals(avanti) && !verificato) {
			if (rispostaCliccata == rispostaCorretta) {
				risposte[rispostaCliccata].setIcon(corretta);
				risposte[rispostaCliccata].removeActionListener(this);
				g.rimuoviSuono();
				g.creaSuono("res/sounds/corretta.wav", false);
			} else {
				risposte[rispostaCliccata].setIcon(sbagliata);
				risposte[rispostaCorretta].setEnabled(true);
				risposte[rispostaCorretta].setIcon(corretta);
				g.rimuoviSuono();
				g.creaSuono("res/sounds/sbagliata.wav", false);
			}
			for (i=0; i<4; i++) {
				risposte[i].removeActionListener(this);
				risposte[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			info.setVisible(true);
			verificato = true;
		} else {
			if (e.getSource().equals(avanti) && info.isVisible()) {
				g.nuovaDomanda();
				info.setVisible(false);
				avanti.setVisible(false);
			}
		}
	}

	
}

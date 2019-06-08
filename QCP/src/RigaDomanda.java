import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

// Questa classe rappresenta una JLabel, la quale racchiude
// le informazioni e gli elementi di ciascuna riga della rubrica.
// Tali elementi sono: nome e cognome del contatto, pulsante chiama
// e pulsante modifica.

@SuppressWarnings("serial")
public class RigaDomanda extends JLabel implements ActionListener {
	private JLabel domanda;
	private Vector<Domanda> domande;
	private JLabel rettangolo = new JLabel(new ImageIcon("res/etc/linea2.png"));
	private JButton modifica = new JButton(new ImageIcon("res/etc/modifica.png"));
	private JButton rimuovi = new JButton(new ImageIcon("res/etc/rimuovi.png"));
	private Font f = new Font(Font.DIALOG, Font.BOLD, 16);
	private GUI g;
	private int cont;
	private Domanda d;
	public RigaDomanda(GUI g, Vector<Domanda> domande, Domanda d, int cont) {
		this.g = g;
		this.d = d;
		this.cont = cont;
		this.domande = domande;
		this.domanda = new JLabel((cont+1)+". "+d.getDomanda());
		this.domanda.setBounds(7, 9, 200, 20);
		this.domanda.setForeground(Color.BLACK);
		this.domanda.setFont(f);
		modifica.setBounds(210, 5, 24, 26);
		modifica.setBackground(Color.black);
		modifica.setBorder(null);
		modifica.setOpaque(false);
		modifica.setFocusable(false);
		modifica.addActionListener(this);
		modifica.setContentAreaFilled(false);
		modifica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(modifica);
		rimuovi.setBounds(238, 5, 24, 26);
		rimuovi.setBackground(Color.black);
		rimuovi.setBorder(null);
		rimuovi.setOpaque(false);
		rimuovi.setFocusable(false);
		rimuovi.addActionListener(this);
		rimuovi.setContentAreaFilled(false);
		rimuovi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(rimuovi);
		rettangolo.setBounds(0, 0, 268, 3);
		add(this.domanda);
		if (cont != 0) {
			add(rettangolo);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(modifica)) {
			g.mostraModifica(cont);
		}
		if (e.getSource().equals(rimuovi)) {
			int risposta = JOptionPane.showConfirmDialog(null, "Vuoi davvero eliminare questa domanda?\n"+d.getDomanda(), "Conferma", JOptionPane.YES_NO_OPTION);
	        if (risposta == JOptionPane.YES_OPTION) {
	        	domande.removeElementAt(cont);
	        	g.tornaIndietro();
	        } else {

	        }
		}
	}

}

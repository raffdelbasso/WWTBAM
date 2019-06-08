import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import javax.swing.*;


@SuppressWarnings("serial")
public class RigaDomanda extends JLabel implements ActionListener {
	private JLabel domanda;
	private Vector<Domanda> domande;
	private JLabel rettangolo = new JLabel(new ImageIcon("res/etc/linea2.png"));
	private JButton scambia = new JButton(new ImageIcon("res/etc/scambia.png"));
	private JButton modifica = new JButton(new ImageIcon("res/etc/modifica.png"));
	private JButton rimuovi = new JButton(new ImageIcon("res/etc/rimuovi.png"));
	private Font f = new Font(Font.DIALOG, Font.BOLD, 16);
	private GUI g;
	private int cont;
	public RigaDomanda(GUI g, Vector<Domanda> domande, Domanda d, int cont) {
		this.g = g;
		this.cont = cont;
		this.domande = domande;
		this.domanda = new JLabel((cont+1)+". "+d.getDomanda());
		this.domanda.setBounds(7, 9, 210, 20);
		this.domanda.setForeground(Color.BLACK);
		this.domanda.setFont(f);
		if (domande.size()>1) {
			scambia.setBounds(219, 5, 25, 27);
			scambia.setBackground(Color.black);
			scambia.setBorder(null);
			scambia.setOpaque(false);
			scambia.setFocusable(false);
			scambia.addActionListener(this);
			scambia.setContentAreaFilled(false);
			scambia.setCursor(new Cursor(Cursor.HAND_CURSOR));
			add(scambia);
		}
		modifica.setBounds(248, 5, 24, 26);
		modifica.setBackground(Color.black);
		modifica.setBorder(null);
		modifica.setOpaque(false);
		modifica.setFocusable(false);
		modifica.addActionListener(this);
		modifica.setContentAreaFilled(false);
		modifica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(modifica);
		rimuovi.setBounds(277, 5, 24, 26);
		rimuovi.setBackground(Color.black);
		rimuovi.setBorder(null);
		rimuovi.setOpaque(false);
		rimuovi.setFocusable(false);
		rimuovi.addActionListener(this);
		rimuovi.setContentAreaFilled(false);
		rimuovi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(rimuovi);
		rettangolo.setBounds(0, 0, 500, 2);
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
			String domandaAccorciata;
			if (domande.elementAt(cont).getDomanda().length()>40) {
				domandaAccorciata = "<html><body color = 'red'>"+
						domande.elementAt(cont).getDomanda().substring(0, 40)+"...</body></html>";
			} else {
				domandaAccorciata = "<html><body color = 'red'>"+domande.elementAt(cont).getDomanda()+"</body></html>";
			}
			int risposta = JOptionPane.showConfirmDialog(null, "Vuoi davvero eliminare questa domanda?\n"+domandaAccorciata, "Conferma", JOptionPane.YES_NO_OPTION);
	        if (risposta == JOptionPane.YES_OPTION) {
	        	domande.removeElementAt(cont);
	        	g.tornaIndietro();
	        }
		}
		if (e.getSource().equals(scambia)) {
			Vector<String> domandeAppoggio = new Vector<String>();
			int dim = this.domande.size();
			int i;
			for (i=0; i<dim; i++) {
				domandeAppoggio.add(this.domande.elementAt(i).getDomanda());
			}
			domandeAppoggio.removeElementAt(cont);
			dim--;
			Object[] domandeScambio = new Object[dim];
			for (i=0; i<domandeAppoggio.size(); i++) {
				if (domandeAppoggio.elementAt(i).length()>40) {
					domandeScambio[i] = domandeAppoggio.elementAt(i).substring(0, 39)+"...";
				} else {
					domandeScambio[i] = domandeAppoggio.elementAt(i);
				}
			}
			Object scelta;
			scelta = JOptionPane.showInputDialog(null, "Con quale domanda vuoi scambiarla?", "Scambio di domande", JOptionPane.QUESTION_MESSAGE, null, domandeScambio, 0);
			int indexScelto = 0;
			boolean trovato = false;
			if (scelta != null) {
				i = 0;
				while(!trovato) {
					if (scelta.equals(domandeScambio[i])) {
						indexScelto = i;
						trovato = true;
					}
					i++;
				}
				if (indexScelto >= cont) {
					indexScelto++;
				}
			    Collections.swap(domande, indexScelto, cont);
			    g.tornaIndietro();
			}
		}
	}

}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class ContainerModificaDomanda extends Container implements ActionListener, DocumentListener {
	private GUI g;
	private Cursor c = new Cursor (Cursor.HAND_CURSOR);
	private JLabel domandaLbl = new JLabel("Domanda:");
	private JLabel rispostaLbl = new JLabel("Risposte:");
	private JTextArea domandaTxt = new JTextArea();
	private JTextField risposta1Txt = new JTextField();
	private JTextField risposta2Txt = new JTextField();
	private JTextField risposta3Txt = new JTextField();
	private JTextField risposta4Txt = new JTextField();
	private JLabel correttaLbl = new JLabel("Risposta corretta:");
	private JComboBox<String> correttaCmb;
	private JLabel daLasciareLbl = new JLabel("Da lasciare con 50:50:");
	private JComboBox<String> daLasciareCmb;
	private JButton conferma = new JButton(new ImageIcon("res/etc/conferma.png"));
	private JButton cancella = new JButton(new ImageIcon("res/etc/cancella.png"));
	private int cont;
	private int posizioneCorrenteCmb1;
	private int posizioneCorrenteCmb2;
	private Vector<Domanda> domande;
	private Border blackBorder = BorderFactory.createLineBorder(Color.black, 3);
	private boolean nuova;
	public ContainerModificaDomanda(GUI g) {
		this.g = g;
		domandaLbl.setBounds(17, 255, 300, 30);
		domandaLbl.setForeground(Color.WHITE);
		domandaLbl.setFont(domandaLbl.getFont().deriveFont(22f));
		add(domandaLbl);
		rispostaLbl.setBounds(17, 355, 100, 30);
		rispostaLbl.setForeground(Color.WHITE);
		rispostaLbl.setFont(domandaLbl.getFont().deriveFont(22f));
		add(rispostaLbl);
		domandaTxt.setBounds(17, 293, 294, 60);
		domandaTxt.setFont(domandaTxt.getFont().deriveFont(14f));
		domandaTxt.setLineWrap(true);
		domandaTxt.setWrapStyleWord(true);
		domandaTxt.getDocument().addDocumentListener(this);
		domandaTxt.setDocument(new JTextFieldLimit(110));
		domandaTxt.setBorder(blackBorder);
		add(domandaTxt);
		risposta1Txt.setBounds(17, 387, 133, 26);
		risposta1Txt.setDocument(new JTextFieldLimit(20));
		risposta1Txt.getDocument().addDocumentListener(this);
		add(risposta1Txt);
		risposta2Txt.setBounds(178, 387, 133, 26);
		risposta2Txt.setDocument(new JTextFieldLimit(20));
		risposta2Txt.getDocument().addDocumentListener(this);
		add(risposta2Txt);
		risposta3Txt.setBounds(17, 417, 133, 26);
		risposta3Txt.setDocument(new JTextFieldLimit(20));
		risposta3Txt.getDocument().addDocumentListener(this);
		add(risposta3Txt);
		risposta4Txt.setBounds(178, 417, 133, 26);
		risposta4Txt.setDocument(new JTextFieldLimit(20));
		risposta4Txt.getDocument().addDocumentListener(this);
		add(risposta4Txt);
		correttaLbl.setBounds(17, 447, 225, 40);
		correttaLbl.setHorizontalAlignment(SwingConstants.LEFT);
		correttaLbl.setForeground(Color.WHITE);
		correttaLbl.setFont(domandaLbl.getFont().deriveFont(22f));
		add(correttaLbl);
		correttaCmb = new JComboBox<String>();
		correttaCmb.setBounds(17, 490, 150, 20);
		add(correttaCmb);
		daLasciareCmb = new JComboBox<String>();
		daLasciareCmb.setBounds(17, 555, 150, 20);
		add(daLasciareCmb);
		aggiornaComboBox();
		daLasciareLbl.setBounds(17, 515, 225, 40);
		daLasciareLbl.setHorizontalAlignment(SwingConstants.LEFT);
		daLasciareLbl.setForeground(Color.WHITE);
		daLasciareLbl.setFont(domandaLbl.getFont().deriveFont(22f));
		add(daLasciareLbl);
		conferma.setBounds(270, 460, 47, 47);
		conferma.setBackground(Color.black);
		conferma.setBorder(null);
		conferma.setOpaque(false);
		conferma.setFocusable(false);
		conferma.addActionListener(this);
		conferma.setContentAreaFilled(false);
		conferma.setCursor(c);
		add(conferma);
		cancella.setBounds(270, 530, 47, 47);
		cancella.setBackground(Color.black);
		cancella.setBorder(null);
		cancella.setOpaque(false);
		cancella.setFocusable(false);
		cancella.addActionListener(this);
		cancella.setContentAreaFilled(false);
		cancella.setCursor(c);
		add(cancella);
	}
	
	public void impostaCampi(Vector<Domanda> domande) {
		nuova = true;
		this.domande = domande;
		domandaTxt.setText("");
		risposta1Txt.setText("");
		risposta2Txt.setText("");		
		risposta3Txt.setText("");
		risposta4Txt.setText("");
		correttaCmb.setSelectedIndex(0);
		daLasciareCmb.setSelectedIndex(0);
	}
	public void impostaCampi(Vector<Domanda> domande, int cont) {
		nuova = false;
		this.cont = cont;
		this.domande = domande;
		domandaTxt.setText(domande.elementAt(cont).getDomanda());
		risposta1Txt.setText(domande.elementAt(cont).getRisposte().elementAt(0));
		risposta2Txt.setText(domande.elementAt(cont).getRisposte().elementAt(1));		
		risposta3Txt.setText(domande.elementAt(cont).getRisposte().elementAt(2));
		risposta4Txt.setText(domande.elementAt(cont).getRisposte().elementAt(3));
		correttaCmb.setSelectedIndex(domande.elementAt(cont).getRispostaCorretta());
		daLasciareCmb.setSelectedIndex(domande.elementAt(cont).getRispostaDaLasciare());

	}
	
	private void aggiornaComboBox() {
		posizioneCorrenteCmb1 = correttaCmb.getSelectedIndex();
		posizioneCorrenteCmb2 = daLasciareCmb.getSelectedIndex();
		correttaCmb.removeAllItems();
		correttaCmb.addItem(risposta1Txt.getText());
		correttaCmb.addItem(risposta2Txt.getText());
		correttaCmb.addItem(risposta3Txt.getText());
		correttaCmb.addItem(risposta4Txt.getText());
		daLasciareCmb.removeAllItems();
		daLasciareCmb.addItem(risposta1Txt.getText());
		daLasciareCmb.addItem(risposta2Txt.getText());
		daLasciareCmb.addItem(risposta3Txt.getText());
		daLasciareCmb.addItem(risposta4Txt.getText());
		correttaCmb.setSelectedIndex(posizioneCorrenteCmb1);
		daLasciareCmb.setSelectedIndex(posizioneCorrenteCmb2);
	}

	public void changedUpdate(DocumentEvent arg0) {}

	public void insertUpdate(DocumentEvent arg0) {
		aggiornaComboBox();
	}

	public void removeUpdate(DocumentEvent arg0) {
		aggiornaComboBox();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(conferma)) {
			if (controllaCampi()) {
				Vector<String> nuoveRisposte = new Vector<String>();
				if (nuova) {
					nuoveRisposte.add(risposta1Txt.getText());
					nuoveRisposte.add(risposta2Txt.getText());
					nuoveRisposte.add(risposta3Txt.getText());
					nuoveRisposte.add(risposta4Txt.getText());
					domande.add(new Domanda(
							domandaTxt.getText(),
							nuoveRisposte,
							correttaCmb.getSelectedIndex(),
							daLasciareCmb.getSelectedIndex()));
					g.tornaIndietro();
				} else {
					domande.elementAt(cont).setDomanda(domandaTxt.getText());
					nuoveRisposte.add(risposta1Txt.getText());
					nuoveRisposte.add(risposta2Txt.getText());
					nuoveRisposte.add(risposta3Txt.getText());
					nuoveRisposte.add(risposta4Txt.getText());
					domande.elementAt(cont).setRisposte(nuoveRisposte);
					domande.elementAt(cont).setRispostaCorretta(correttaCmb.getSelectedIndex());
					domande.elementAt(cont).setRispostaDaLasciare(daLasciareCmb.getSelectedIndex());
					g.tornaIndietro();
				}
			}
		}
		if (arg0.getSource().equals(cancella)) {
			g.tornaIndietro();
		}
	}

	private boolean controllaCampi() {
		boolean ok;
		ok = true;
		if (domandaTxt.getText().equals("")) {
			ok = false;
			domandaTxt.setBackground(Color.decode("#a5000b"));
			domandaTxt.setForeground(Color.white);
			domandaTxt.setCaretColor(Color.white);
		} else {
			domandaTxt.setBackground(Color.white);
			domandaTxt.setForeground(Color.black);
			domandaTxt.setCaretColor(Color.black);
		}
		if (risposta1Txt.getText().equals("")) {
			ok = false;
			risposta1Txt.setBackground(Color.decode("#a5000b"));
			risposta1Txt.setForeground(Color.white);
			risposta1Txt.setCaretColor(Color.white);
		} else {
			risposta1Txt.setBackground(Color.white);
			risposta1Txt.setForeground(Color.black);
			risposta1Txt.setCaretColor(Color.black);
		}
		if (risposta2Txt.getText().equals("")) {
			ok = false;
			risposta2Txt.setBackground(Color.decode("#a5000b"));
			risposta2Txt.setForeground(Color.white);
			risposta2Txt.setCaretColor(Color.white);
		} else {
			risposta2Txt.setBackground(Color.white);
			risposta2Txt.setForeground(Color.black);
			risposta2Txt.setCaretColor(Color.black);
		}
		if (risposta3Txt.getText().equals("")) {
			ok = false;
			risposta3Txt.setBackground(Color.decode("#a5000b"));
			risposta3Txt.setForeground(Color.white);
			risposta3Txt.setCaretColor(Color.white);
		} else {
			risposta3Txt.setBackground(Color.white);
			risposta3Txt.setForeground(Color.black);
			risposta3Txt.setCaretColor(Color.black);
		}
		if (risposta4Txt.getText().equals("")) {
			ok = false;
			risposta4Txt.setBackground(Color.decode("#a5000b"));
			risposta4Txt.setForeground(Color.white);
			risposta4Txt.setCaretColor(Color.white);
		} else {
			risposta4Txt.setBackground(Color.white);
			risposta4Txt.setForeground(Color.black);
			risposta4Txt.setCaretColor(Color.black);
		}
		if (correttaCmb.getSelectedIndex() == daLasciareCmb.getSelectedIndex()) {
			ok = false;
			correttaCmb.setBackground(Color.decode("#a5000b"));
			correttaCmb.setForeground(Color.white);
			daLasciareCmb.setBackground(Color.decode("#a5000b"));
			daLasciareCmb.setForeground(Color.white);
		} else {
			correttaCmb.setBackground(Color.white);
			correttaCmb.setForeground(Color.black);
			daLasciareCmb.setBackground(Color.white);
			daLasciareCmb.setForeground(Color.black);
		}
		return ok;
	}

	private class JTextFieldLimit extends PlainDocument {
		private int limit;
		public JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str != "\n") {
				if (getLength() + 1 <= limit) {
					super.insertString(offset, str, attr);
			    }
			}
		}
	}
}

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, WindowListener {
	private Image icona;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon sfondo = new ImageIcon("res/etc/sfondoPnl.jpg");
	private Vector<Domanda> domande;
	private ContainerDomande c;
	private ContainerModificaDomanda c2;
	private JButton aggiungi = new JButton(new ImageIcon("res/etc/aggiungi.png"));
	public GUI(Vector<Domanda> domande) {
		this.domande = domande;
		icona = Toolkit.getDefaultToolkit().getImage("res/etc/logo.png");
		setIconImage(icona);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		addWindowListener(this);
		setResizable(false);
		setBounds(0, 0, sfondo.getIconWidth(), sfondo.getIconHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2));
		setTitle("Chi vuol essere milionario - Pannello di controllo");
		pack();
		c = new ContainerDomande(this, domande);
		c.setBounds(0, 0, sfondo.getIconWidth(), sfondo.getIconHeight());
		c2 = new ContainerModificaDomanda(this);
		c2.setBounds(0, 0, sfondo.getIconWidth(), sfondo.getIconHeight());
		c.setVisible(true);
		c2.setVisible(false);
		aggiungi.setBounds(28, 528, 281, 62);
		aggiungi.setBackground(Color.black);
		aggiungi.setBorder(null);
		aggiungi.setOpaque(false);
		aggiungi.setFocusable(false);
		aggiungi.addActionListener(this);
		aggiungi.setContentAreaFilled(false);
		aggiungi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(aggiungi);
		add(c);
		add(c2);
		repaint();
		revalidate();
	}
	
	public void mostraModifica(int cont) {
		c.setVisible(false);
		c2.setVisible(true);
		c2.impostaCampi(domande, cont);
		aggiungi.setVisible(false);
	}
	
	public void tornaIndietro() {
		c2.setVisible(false);
		c.setVisible(domande.size() != 0);
		aggiungi.setVisible(true);
		c.creaElenco();
	}
	
	public void windowClosing(WindowEvent e) {
		if (e.getSource().equals(this)) {
			Writer writer = null;
			try {
			    writer = new OutputStreamWriter(new FileOutputStream("../domandeRisposte.txt"), StandardCharsets.UTF_8);
				int i;
				for (i=0; i<domande.size(); i++) {
					writer.write(domande.elementAt(i).getDomanda()+"\n");
					writer.write(domande.elementAt(i).getRisposte().elementAt(0)+"\n");
					writer.write(domande.elementAt(i).getRisposte().elementAt(1)+"\n");
					writer.write(domande.elementAt(i).getRisposte().elementAt(2)+"\n");
					writer.write(domande.elementAt(i).getRisposte().elementAt(3)+"\n");
					writer.write(domande.elementAt(i).getRispostaCorretta()+"\n");
					writer.write(domande.elementAt(i).getRispostaDaLasciare()+"\n");
				}
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {}

	public void windowDeactivated(WindowEvent arg0) {}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(aggiungi)) {
			c.setVisible(false);
			c2.setVisible(true);
			c2.impostaCampi(domande);
			aggiungi.setVisible(false);
		}
	}
}

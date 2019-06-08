import java.awt.Container;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ContainerDomande extends Container {
	private Vector<RigaDomanda> nomi;
	private JScrollPane scorrimento;
	private JPanel pnl = new JPanel();
	private Vector<Domanda> domande;
	private GUI g;
	public ContainerDomande(GUI g, Vector<Domanda> domande) {
		this.g = g;
		this.domande = domande;
		creaElenco();
	}
	
	public void creaElenco() {
		removeAll();
		pnl.removeAll();
		pnl.setLayout(null);
		nomi = new Vector<RigaDomanda>();
		for (int i=0; i<domande.size(); i++) {
			nomi.add(new RigaDomanda(g, domande, domande.elementAt(i), i));
			nomi.elementAt(i).setBounds(0, (i*37), 268, 37);
			pnl.add(nomi.elementAt(i));
		}
		
		// Tutte le righe create saranno inserite in un pannello,
		// il quale sarà inserito in uno JScrollPanel,
		// in grado di scorrere se le domande raggiungono un certo numero.
		pnl.setPreferredSize(new Dimension(1, domande.size()*37));
		scorrimento = new JScrollPane(pnl);
		scorrimento.setBounds(32, 296, 268, 220);
		scorrimento.setPreferredSize(new Dimension(248, 170));
		scorrimento.getVerticalScrollBar().setUnitIncrement(5);
		scorrimento.setVisible(domande.size()!=0);
		add(scorrimento);
		revalidate();
	}
	
}

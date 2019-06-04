
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Carica extends TimerTask{
	GUI gui;
	public Carica (GUI gui){
		this.gui=gui;
	}

	public void run() {
		gui.getContentPane().removeAll();
		gui.setContentPane(new JLabel(new ImageIcon("res/etc/loading.jpg")));
		gui.getContentPane().revalidate();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui.setContentPane(new JLabel(gui.sfondo2));
		gui.domanda = new Domanda(gui);
		gui.domanda.setBounds(0, 0, gui.sfondo.getIconWidth(), gui.sfondo.getIconHeight());
		gui.domanda.setVisible(true);
		gui.add(gui.domanda);
		gui.nuovaDomanda();
	}
}
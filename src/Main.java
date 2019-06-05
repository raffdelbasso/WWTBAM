import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		GUI g = new GUI();
		String temp;
		String domanda;
		Vector<String> risposte;
		int corretta;
		int daLasciare;
		int i;
		try(BufferedReader br = new BufferedReader(new FileReader("domandeRisposte.txt"))) {
			// Lettura della prima riga
			temp = br.readLine();
			// Se non è null (se c'è qualcosa)
		    while (temp != null) {
		    	domanda = temp;
		    	risposte = new Vector<String>();
		    	for (i=0; i<4; i++) {
		    		risposte.add(br.readLine());
		    	}
		       	corretta = Integer.valueOf(br.readLine());
		       	daLasciare = Integer.valueOf(br.readLine());
		       	g.impostaDomanda(domanda, risposte, corretta, daLasciare);
		       	temp = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

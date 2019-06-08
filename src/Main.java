import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws IOException {
		GUI g = new GUI();
		String temp;
		String domanda;
		Vector<String> risposte;
		int corretta;
		int daLasciare;
		int i;
		File fileDir = new File("domandeRisposte.txt");
		boolean almenoUna = false;
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			temp = in.readLine();
		    while (temp != null) {
		    	almenoUna = true;
		    	domanda = temp;
		    	risposte = new Vector<String>();
		    	for (i=0; i<4; i++) {
		    		risposte.add(in.readLine());
		    	}
		    	corretta = Integer.valueOf(in.readLine());
		       	daLasciare = Integer.valueOf(in.readLine());
		       	g.impostaDomanda(domanda, risposte, corretta, daLasciare);
		       	temp = in.readLine();
		    }
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!almenoUna) {
			g.nascondiTasti();
		}
	}

}

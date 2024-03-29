import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(String[] args) throws IOException {
		String temp;
		String domanda;
		Vector<String> risposte;
		Vector<Domanda> domande = new Vector<Domanda>();
		int corretta;
		int daLasciare;
		int i;
		File fileDir = new File("../domandeRisposte.txt");
		
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			temp = in.readLine();
		    while (temp != null) {
		    	domanda = temp;
		    	risposte = new Vector<String>();
		    	for (i=0; i<4; i++) {
		    		risposte.add(in.readLine());
		    	}
		    	corretta = Integer.valueOf(in.readLine());
		       	daLasciare = Integer.valueOf(in.readLine());
		       	domande.add(new Domanda(domanda, risposte, corretta, daLasciare));
		       	temp = in.readLine();
		    }
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		GUI g = new GUI(domande);
	}

}

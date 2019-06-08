import java.util.Vector;

public class Domanda {
	private String domanda;
	private Vector<String> risposte;
	private int rispostaCorretta;
	private int rispostaDaLasciare;
	public Domanda(String domanda, Vector<String> risposte, int rispostaCorretta, int rispostaDaLasciare) {
		super();
		this.domanda = domanda;
		this.risposte = risposte;
		this.rispostaCorretta = rispostaCorretta;
		this.rispostaDaLasciare = rispostaDaLasciare;
	}
	public String getDomanda() {
		return domanda;
	}
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}
	public Vector<String> getRisposte() {
		return risposte;
	}
	public void setRisposte(Vector<String> risposte) {
		this.risposte = risposte;
	}
	public int getRispostaCorretta() {
		return rispostaCorretta;
	}
	public void setRispostaCorretta(int rispostaCorretta) {
		this.rispostaCorretta = rispostaCorretta;
	}
	public int getRispostaDaLasciare() {
		return rispostaDaLasciare;
	}
	public void setRispostaDaLasciare(int rispostaDaLasciare) {
		this.rispostaDaLasciare = rispostaDaLasciare;
	}
	
}

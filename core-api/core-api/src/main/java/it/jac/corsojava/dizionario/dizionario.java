package it.jac.corsojava.dizionario;

import java.util.ArrayList;

public class dizionario {
	
	public ArrayList<String> ingita = new ArrayList<>();
	public ArrayList<String> fraita = new ArrayList<>();
	public ArrayList<String> tedita = new ArrayList<>();
	public String[] lingue= {"inglese", "francese", "tedesco"};
	public String lingua = "";
		
	public boolean checkLingua(String str) {
		boolean risultato = false;
		for (int i = 0; i<this.lingue.length; i++) {
			if (this.lingue[i].equals(str)) {
				this.lingua=str;
				risultato = true;
			}
		}
		return risultato;
		
	}
	
	public void setParola(String parola) {
		if (this.lingua.equals("inglese")) {
			this.ingita.add(parola);
		} else if (this.lingua.equals("francese")) {
			this.fraita.add(parola);
		} else if (this.lingua.equals("tedesco")) {
			this.tedita.add(parola);
		}
	}

	public String getLingua() {
		return lingua;
	}

	public String getLingue() {
		String risultato = "";
		for (int i = 0; i<lingue.length; i++) {
			risultato += " " + lingue[i];
		}
		return risultato;
		
	}
	
	public String getParola(String ita) {
		String risultato = "Parola non presente nel dizionario";
		if (this.lingua.equals("inglese")) {
			for (int i = 0; i<this.ingita.size(); i = i + 2) {
				if (this.ingita.get(i).equals(ita)) {
					risultato = this.ingita.get(i+1);
					break;
				}
			}
		} else if (this.lingua.equals("francese")) {
			for (int i = 0; i<this.fraita.size(); i = i + 2) {
				if (this.fraita.get(i).equals(ita)) {
					risultato = this.fraita.get(i+1);
					break;
				}
			}
		} else if (this.lingua.equals("tedesco")) {
			for (int i = 0; i<this.tedita.size(); i = i + 2) {
				if (this.tedita.get(i).equals(ita)) {
					risultato = this.tedita.get(i+1);
					break;
				}
			}
		}
		
		return risultato;
	}
		
}


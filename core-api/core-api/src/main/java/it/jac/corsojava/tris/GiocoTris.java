package it.jac.corsojava.tris;

import java.io.PrintStream;

public class GiocoTris {

	private boolean partitaInCorso;
	private int turno;
	private String vincitore;
	private long startTime;
	private long endTime;
	private char[] campo = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };

	public void iniziaPartita() {
		
		this.partitaInCorso = true;
		this.turno = 0;
		this.vincitore = "";
		this.startTime = System.currentTimeMillis();
		this.endTime = 0;
		this.campo = new char[] {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
	}
	
	public String getSimboloTurno() {
		
		return ((this.turno % 2) == 0) ? "X" : "O";
	}
	
	public String gioca(int posizione) {
		
//		controllare che la partita sia in corso
		if (!this.partitaInCorso) {
			return "Partita non iniziata!";
		}
		
//		controllare che il numero sia tra 1 e 9
		if (posizione < 1 || posizione > 9) {
			return "Inserire un valore tra 1 e 9";
		}
		
//		controllare che nella posizione non ci sia già un valore
		if (campo[posizione - 1] != ' ') {
			return "Posizione occupata";
		}
		
		campo[posizione - 1] = getSimboloTurno().charAt(0);
		this.turno++;
		
		return null;
	}
	
	public void stampaCampo(PrintStream stream) {
		
		for(int i = 0;i < 9;i++) {
			
			stream.print(campo[i]);
			if ((i + 1) % 3 == 0) {
				stream.println();
			} else {
				stream.print(" | ");
			}
		}
	}
	
	public boolean isPartitaInCorso() {
		return partitaInCorso;
	}

	public int getTurno() {
		return turno;
	}

	public String getVincitore() {
		return vincitore;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

}

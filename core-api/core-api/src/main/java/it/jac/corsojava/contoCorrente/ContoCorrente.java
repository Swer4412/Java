package it.jac.corsojava.contoCorrente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ContoCorrente implements CCInterfaccia {

	ArrayList<Movimento> movimenti = new ArrayList<Movimento>();
	
	public void deposita(int num) {
		movimenti.add(new Movimento(num));	
	}
	
	public String preleva(int num) {
		String msg;
		if (Movimento.getSaldo()>=num) {
			movimenti.add(new Movimento(-num));
			msg="Effettuato con successo";
		} else {
			msg="Saldo troppo basso";
		}
		return msg;
		
		
	}
	
	public String getMovimenti(String datain) {
		//Se premo invio senza scrivere niente, prendo la data odierna
		if (datain=="") {
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			datain = localDate.format(formatter);
		}
		//Prendo data inizio e data fine
		LocalDate dataInizio = getDataInizio(datain);
		LocalDate dataFine = getDataFine(datain);
		//Dichiario variabili utili
		String desc;
		int somma = 0;
		LocalTime ora;
		LocalDate data;
		String stringa = "";
		
		for (int i = 0; i < movimenti.size(); i++) {
			Movimento obj = movimenti.get(i);
			if (obj.data.compareTo(dataInizio)>=0 && obj.data.compareTo(dataFine)<=0) {
				desc= obj.desc;
				somma = obj.somma;
				data = obj.data;
				ora = obj.ora;
				//Creo una lunga strina che contiene tutti i dati
				stringa = stringa 
						+ desc 
						+ "\nSomma: " + somma 
						+ "\nData: " + data 
						+ "\nOra: " + ora 
						+ "\n--------------------------\n";
			}
		}
		
		return stringa;
	}
	
	public LocalDate getDataInizio(String data) {
		String dt = data.substring(0,10);
		LocalDate dataIni = LocalDate.parse(dt);
		return dataIni;
	}
	
	public LocalDate getDataFine(String data) {
		String dt = data.substring(data.length() -10);
		LocalDate dataFin = LocalDate.parse(dt);
		return dataFin;
	}
	
	public int getSaldo() {
		return Movimento.getSaldo();
	}
	
	public boolean ricaricaTel(int num) {
		movimenti.add(new Movimento(-num));
		return true;
	}
}

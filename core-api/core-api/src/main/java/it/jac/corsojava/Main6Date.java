package it.jac.corsojava;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

public class Main6Date {

	public static void main(String[] args) {
		
//		########################
//		da Java 8 in poi
//		########################
		
		LocalDate data = LocalDate.of(2020, Month.OCTOBER, 20); //d/m/Y
		
		LocalTime orario = LocalTime.of(10, 50); //H:M:S
		
		LocalDateTime dataOra = LocalDateTime.of(2020, 10, 20, 10, 50); 
		
		System.out.println("Data " + data);
		System.out.println("Orario " + orario);
		System.out.println("Data e ora " + dataOra);
		
		// così come l'oggetto String anche LocalDate, LocalTime e LocalDateTime sono immutabili
		
		LocalDate data2 = LocalDate.now();
		
		System.out.println("Data " + data2);
		
		LocalDate now = LocalDate.now();
		
		System.out.println("Now " + now);
		
		System.out.println("Data futura " + data.plusDays(1).plusMonths(2).plusYears(5));
		
		if (data.isAfter(now)) {
			System.out.println("data maggiore di oggi");
		} else {
			System.out.println("data minore di oggi");
		}
		
//		stampa delle informazioni contenute nella data
		System.out.println("Output dataOra: " + dataOra);
		System.out.println("giorno della settimana: " + dataOra.getDayOfWeek().name());
		System.out.println("giorno: " + dataOra.getDayOfMonth());
		System.out.println("mese: " + dataOra.getMonth());
		System.out.println("anno: " + dataOra.getYear());
		System.out.println("ore: " + dataOra.getHour());
		System.out.println("minuti: " + dataOra.getMinute());
		System.out.println("secondi: " + dataOra.getSecond());
		
//		se voglio stampare tutte le informazioni seguendo un formato scelto da me
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //Il DateTimeFormatter prende una stringa che 
		// descrive come si vuole formattare una data
		System.out.println("dataora formattata: " + formatter.format(dataOra));
		
		DateTimeFormatter formatterShort = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println("dataora formattata SHORT: " + formatterShort.format(dataOra));

		DateTimeFormatter formatterMedium = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println("dataora formattata MEDIUM: " + formatterMedium.format(dataOra));
		
		String dataUtenteStr = "25/12/2000";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate dataUtente = LocalDate.from(dtf.parse(dataUtenteStr));
		
		System.out.println("Data utente dopo parsing " + dataUtente);
		
		long timeDaysCalculated = ChronoUnit.DAYS.between(now, dataUtente);
		long timeMonthCalculated = ChronoUnit.MONTHS.between(now, dataUtente);
		
		System.out.println("Differenza tra le date (giorni) " + timeDaysCalculated);
		System.out.println("Differenza tra le date (mesi) " + timeMonthCalculated);
		
		System.out.println("Now formattato secondo il nostro pattern " + dtf.format(now));
		
		LocalDate date = LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
		date.plusDays(2);
		System.out.println(date.getYear() + " " + date.getMonth() + " "
		+ date.getDayOfMonth());
		
//		############################
//		nelle versioni precedenti
//		############################
		
		Date oldDate = new Date();
		
		System.out.println("Old date " + oldDate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(oldDate);
		cal.add(Calendar.DAY_OF_MONTH, 5);
		System.out.println("old date modificata " + cal.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String oldDateFormatted = sdf.format(oldDate);
		
		System.out.println("old date formattata " + oldDateFormatted);
	}
	
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	Definire le classi necessarie a scrivere un programma in grado di simulare una gestione semplificata di un conto corrente.
//	In particolare il conto corrente ammette le seguenti operazioni:
//
//		deposito - il programma permette la registrazione di un movimento che incrementa il saldo del conto corrente
//		prelievo - il programma permette la registrazione di un movimento che decrementa il saldo del conto corrente
//		elenco movimenti - il programma permette di visualizzare tutti i movimenti registrati in un lasso di tempo (data inizio e data fine)
//		saldo - il programma permette di visualizzare il saldo attuale contenuto nel conto corrente
//		ricarica telefonica - il programma permette di eseguire una ricarica telefonica	ricarica(numTelefono)
// =====================================================	
	
}

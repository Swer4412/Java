package it.jac.corsojava;

import java.time.LocalDate;

import it.jac.corsojava.bean.Fattura;
import it.jac.corsojava.bean.FatturaErrata;

public class Main6Encapsulation {

	public static void main(String[] args) {
		
//		primo esempio di utilizzo di una Fattura che non fa uso dell'incapsulamento
		
		FatturaErrata fattura1 = new FatturaErrata();
		fattura1.numeroFattura = "1-2021";
//		esempi di valorizzazione errata, non c'è nulla che mi blocca nell'impostare un valore errato
//		fattura1.numeroFattura = "1/2021";
//		fattura1.numeroFattura = "2021/1";
//		fattura1.numeroFattura = "2021";
		fattura1.dataFattura = LocalDate.now().plusDays(1);
		fattura1.cliente = "Cliente di prova1";
//		di nuovo posso impostare il valore di un attributo in modo improprio
		fattura1.importo = -45;
//		anche dopo aver impostato tutti gli attributi posso ancora modificare il numero fattura
		fattura1.numeroFattura = "2-2021";
		System.out.println(fattura1);
		
		Fattura fattura2 = new Fattura(2021, 1);
//		un valore futuro non verrà accettato
		fattura2.setDataFattura(LocalDate.now().plusDays(1));
//		un valore negativo non verrà accettato
		fattura2.setImporto(-45);
		fattura2.setCliente("Cliente di prova2");
		System.out.println(fattura2);

		Fattura fattura3 = new Fattura(2021, 1);
//		un valore futuro non verrà accettato
		fattura3.setDataFattura(LocalDate.now().plusDays(-1));
//		un valore negativo non verrà accettato
		fattura3.setImporto(45);
		fattura3.setCliente("Cliente di prova3");
		System.out.println(fattura3);

	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	implementare una classe che simula una vettura in movimento
//	la vettura ha 3 velocità
//	a velocità 1 percorre 15 metri al secondo
//	a velocità 2 percorre 30 metri al secondo
//	a velocità 3 percorre 40 metri al secondo
//
//	le operazioni che è possibile richiamare sulla vettura sono
//	- parti (velocità 0 inizialmente)
//	- accelera (aumento di 1 la velocità)
//	- frena (diminuisco di 1 la velocità)
//	- stop (termino la corsa, non posso più ripartire o accelerare o decelerare)
//	- calcolo metri (restituisce il numero di metri percorsi dalla partenza e il tempo totale della corsa)
//
//	- implementare la classe che utilizza dei casi d'uso per verificarne il comportamento	
}

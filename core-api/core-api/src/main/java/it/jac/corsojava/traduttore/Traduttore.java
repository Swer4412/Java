package it.jac.corsojava.traduttore;

public interface Traduttore { //é un interfaccia, non esegue niente
	// Le interfaccie vengono usate per obbigare a implementare queste funzioni
	// Permette di definire una struttura rigida
	// Un traduttore puo salvare le parole in ram, file, database...
	// Questa interfaccia fa in modo che main funzioni se che io salvi su file o database
	// Appena creo una classe che implementa questa interfaccia (public class implements interfaccia), quella classe ti obbliga
	// a scrivere funzioni che accettano i valori indicati(String vocabolo) e che ritornano
	// il tipo indicato (String)
	void registraInglese(String vocaboloEn, String traduzione);

	void registraFrancese(String vocaboloFr, String traduzione);

	String traduciInglese(String vocaboloEn);

	String traduciFrancese(String vocaboloFr);

}
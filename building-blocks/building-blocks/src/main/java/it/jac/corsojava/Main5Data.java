package it.jac.corsojava;

public class Main5Data {

	{
//		System.out.println("valore di giorno " + giorno); 
//		non compila perche' l'istruzione e' inserita prima della dichiarazione della variabile
	}
	
	int giorno;
	int mese;
	int anno;
	
	{ // Eseguita tutte le volte che viene costruito una nuova istanza(oggetto) (blocco di inizializzazione)
		System.out.println("impostazione field");
		giorno = 10;
		System.out.println("valore di giorno " + giorno);
	}
	
	static { //Eseguita una sola volta appena viene esguito il programma e poi non viene più eseguito
		System.out.println("impostazione statica");
	}
	
	//Il costruttore non é obbligatorio
	public Main5Data() { //Costruttore, per essere un costruttore esso deve avere lo stesso nome della classe, non deve avere alcun tipo di return type,
		// Se viene messo qualcosa nella parentesi, essi devono essere passati per la costruzione
		giorno = 1;
		mese = 1;
		anno = 2021;
		System.out.println("chiamato il costruttore");
	}
	
	String stampa() { //Funzione di Main5data
		
		return giorno + "/" + mese + "/" + anno;
	} //stampa riesce a prendere giorno mese e anno perche si trova nello stesso scope di Main5data 
	
	
	public static void main(String[] args) {
		
		System.out.println("chiamato il metodo main");
		
		Main5Data d = new Main5Data(); //
		//new crea una copia della classe (istanza) Main5data nella ram
		System.out.println(d.stampa());
		
		d.giorno = 20;
		d.mese = 11;
		d.anno = 2022;
		
		System.out.println(d.stampa());

		Main5Data data2 = new Main5Data();
		System.out.println(data2.stampa()); //Stampa ancora 1/1/2021 perche il costruttore da questi valori
		
		data2.giorno = 12;
		data2.mese = 4;
		data2.anno = 2020;
		
		System.out.println(data2.stampa());

		Main5Data d3 = new Main5Data();
		System.out.println(d3.stampa());
		
		d3.giorno = 8;
		d3.mese = 1;
		d3.anno = 2030;
		
		System.out.println(d3.stampa());

	
	}
	
	
// ====================================================
//	ESERCITAZIONE INSIEME
//	Scrivere una programma che dichiara 2 variabili intere e usa una funzione per calcolare la somma delle variabili
//	il valore restituito dalla funzione viene poi stampato in output
// =====================================================	
}

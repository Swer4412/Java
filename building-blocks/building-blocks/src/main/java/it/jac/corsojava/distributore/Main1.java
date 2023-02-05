package it.jac.corsojava.distributore;

public class Main1 {
	

	// Dichiaro i colori da usare nella console
	public static final String VERDE = "\u001B[32m";
	public static final String BLU = "\u001B[34m";
	public static final String AZZURRO= "\u001B[36m";
	
	public static void main(String[] args) {
		
		//Inizio interfaccia della consolee
		System.out.println("DISTRIBUTORE");
		System.out.println(String.format("%20s | %20s | %20s | %20s |", "Codice Distributore", "Acqua Naturale", "Merendine", "The"));
		
		//Creo un nuovo distributore
		Distributore d1; //Fa una copia della classe nella variabile d1
		d1 = new Distributore(); //Popola la ram con ciò che c'è nel costruttore
		d1.setCodice("#00001"); //Creare setcodice è solo una formalità infatti si può usare tranquillamente: String dd = d1.codice;
		int nAcqua = d1.getAcquaNaturale();
		int nMerendine = d1.getMerendine();
		int nThe = d1.getThe();
		
		d1.printInfo(VERDE); 
		
		//Simulo un prelievo
		d1.preleva(3, 5, 7);
		
		//Aggiorno i dati 
		nAcqua = d1.getAcquaNaturale();
		nMerendine = d1.getMerendine();
		nThe = d1.getThe();
		
		//Stampo i dati aggiornati
		d1.printInfo(VERDE);
		
		//Repeat :)
		
		Distributore d2 = new Distributore();
		d2.setCodice("#00002");
		nAcqua = d2.getAcquaNaturale();
		nMerendine = d2.getMerendine();
		nThe = d2.getThe();
		
		d2.printInfo(BLU); 
		
		//Simulo un prelievo
		d2.preleva(7, 1, 0);
		
		nAcqua = d2.getAcquaNaturale();
		nMerendine = d2.getMerendine();
		nThe = d2.getThe();
		
		d2.printInfo(BLU);
		
		
		Distributore d3 = new Distributore();
		d3.setCodice("#00003");
		nAcqua = d3.getAcquaNaturale();
		nMerendine = d3.getMerendine();
		nThe = d3.getThe();
		
		d3.printInfo(AZZURRO); 
		
		//Simulo un prelievo
		d3.preleva(2, 6, 9);
		
		nAcqua = d3.getAcquaNaturale();
		nMerendine = d3.getMerendine();
		nThe = d3.getThe();
		
		d3.printInfo(AZZURRO); 
	}
}

package it.jac.corsojava;

import java.util.ArrayList;

public class Main3List {

	public static void main(String[] args) {

		String s = null;
		
		ArrayList list = new ArrayList();

		System.out.println("list1 size: " + list.size());

		ArrayList list2 = new ArrayList(5);
		
		System.out.println("list2 size: " + list2.size());
		
		list2.add(1);
		ArrayList list3 = new ArrayList(list2);
		
		System.out.println("list3 size: " + list3.size());
		
//		dalla versione Java 1.5 � possibile specificare il tipo di dato contenuto nella lista
//		con l'uso dei "generics"
		ArrayList<String> list4 = new ArrayList<>();

//		questa operazione non � consentita perch� nella variabile "list4" il compilatore si aspetta di poter gestire solo elementi
//		di tipo "String"
//		list4.add(1);
		
//		----------------------------------------------------------------------------
		
		list.add(new Integer("1"));
		list.add(10);
		list.add(3);
		
		System.out.println(list);
		
		list.add("stringa");
		
		list.add(30.30);
		
		System.out.println(list);

		for(Object obj : list) {
			
			System.out.println(obj.getClass().getName());
		}
		
		for(int i = 0;i < list.size();i++) {
			
			Object obj = list.get(i);
			
		}
		
		System.out.println("Dimensione dell'array " + list.size());
		
		list.remove(3);
		list.remove(new Integer(3));
		
		System.out.println(list);
		
		list.add(2, "nuovo valore");
		
		System.out.println(list);
		
		list.clear();
		
		System.out.println("lista dopo pulizia " + list);
		
		System.out.println("lista vuota? " + list.isEmpty());
		
		list.add("Rosso");
		list.add("Verde");
		list.add("Giallo");
		
		System.out.println(list.contains("Rosso"));
		
	}
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//  implementare una classe in grado di simulare un traduttore
//  la classe permette di registare al suo interno parole in lingua inglese e francese
//  ad ogni parola registrata deve corrispondere la sua traduzione in italiano
//  la classe deve esporre le seguenti operazioni
// 		registra nuova parola inglese (viene specificata la parola inglese e la sua traduzione)
//		registra nuova parola francese (viene specificata la parola francese e la sua traduzione)
//		traduci parola inglese (viene richiesta in input la parola da tradurre)
//		traduci parola francese (viene richiesta in input la parola da tradurre)
//	implementare la classe che utilizza dei casi d'uso per verificarne il comportamento
// =====================================================	

	
	
	
	
	
	
	
	
	
	
	
	
	
}

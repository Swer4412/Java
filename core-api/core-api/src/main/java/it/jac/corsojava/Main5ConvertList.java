package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main5ConvertList {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<>();
		list.add("Mercurio");
		list.add("Venere");
		list.add("Marte");

		Object[] array = list.toArray(); //Converto arrylist in array
		System.out.println(Arrays.toString(array));
		
		String[] arrayString = list.toArray(new String[0]);
		System.out.println(Arrays.toString(arrayString));
		
//		la variabile � dichiarata come "List" e non come "ArrayList"!
		List<String> list2 = Arrays.asList(arrayString); //Passo un array
		System.out.println(list2);
		
		List<String> list3 = Arrays.asList("Lombardia", "Piemonte", "Liguria"); //Passo più stringhe
		System.out.println(list3);
	}
}
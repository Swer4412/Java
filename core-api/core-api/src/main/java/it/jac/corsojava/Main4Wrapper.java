package it.jac.corsojava;

import java.util.ArrayList;
import java.util.List;

public class Main4Wrapper {

	public static void main(String[] args) {
		
//   	boolean		Boolean 	new Boolean(true)
//		byte 		Byte 		new Byte((byte) 1)
//		short 		Short 		new Short((short) 1)
//		int 		Integer 	new Integer(1)
//		long 		Long 		new Long(1)
//		float 		Float 		new Float(1.0)
//		double 		Double 		new Double(1.0)
//		char 		Character 	new Character('c')
		
		List list = new ArrayList();
		list.add(100); // in automatico viene costruita la classe Interger contenente il valore 100
		
		Integer i = 10; // � come scrivere Integer i = new Integer(10);
		System.out.println("i = " + i);
		
		int n2 = i.intValue();
		System.out.println("n2 = " + n2);
		
//		i = null;
		int j = i; // in automatico viene estratto il valore dalla classe
		System.out.println("j = " + j);
		
		// come succede se i � nullo???
		
		System.out.println("valore i " + i);
		System.out.println("valore j " + j);
		
		Boolean b = Boolean.TRUE;
		
		if (b) {
			System.out.println("condizione vera");
		} else {
			System.out.println("condizione falsa");
		}
		
	}
}

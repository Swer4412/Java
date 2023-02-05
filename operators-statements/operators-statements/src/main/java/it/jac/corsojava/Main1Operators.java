package it.jac.corsojava;

import java.util.Random;

public class Main1Operators {

	public static void main(String[] args) {
		
		operatorPrecedence();
		
		numericPromotion();
		
		unaryOperators();
		
		additionalOperators();
		
	}
	
	private static void operatorPrecedence() {
		
		int result = 2 * 4 + 10;
		System.out.println("2 * 4 + 10 = " + result);
		
//		equivale a scrivere (2 * 4) + 10;
//		viene data precedenza agli operatori (*, /, %)
		
//		se necessitiamo di imporre il nostro ordine di priorit� allora dobbiamo esplicitarlo con le parentesi
		int result2 = 2 * (4 + 10);
		System.out.println("2 * (4 + 10) = " + result2);
		
//		operatore modulo (%) restituisce il resto della divisione (da non confondere con /)
		System.out.println("14 / 7 = " + (14 / 7)); // 2
		
		System.out.println("14 % 7 = " + (14 % 7)); // 0 
		
		System.out.println("15 / 7 = " + (15 / 7)); // 2
		
		System.out.println("15 % 7 = " + (15 % 7)); // 1
		
	}

	private static void numericPromotion() {
		
//		regole
		
//		se due valori sono di tipologia differente, Java automaticamente promuove un valore a quello della tipologia pi� grande tra i 2
		int i1 = 10;
		long l1 = 3;
		System.out.println("int * long = " + printType(i1 * l1)); // Long riserva più spazio in memoria quindi è più grande
		
//		se un valore � intero e l'altro � a virgola mobile, Java automaticamente promuove il valore intero in virgola mobile
		int i2 = 5;
		float f2 = 2.5f;
		System.out.println("int + float = " + printType(i2 * f2)); // Chiaramente perchè se no il risultato verrebbe arrotondato 
		
//		i valori di tipo (byte, short e char) sono promossi in valori interi quando usati con operatori aritmetici anche se nessun valore � intero
		byte b3 = 9;
		short s3 = 5;
		System.out.println("byte / short = " + printType(b3 * s3));
		
	}

	private static String printType(Object o) {
		return o.getClass().getName();
	}

	private static void unaryOperators() {
		
//		operatore !
		boolean flag = false;
		System.out.println("!flag = " + (!flag));
		System.out.println("flag = " + (flag));
		
//		operatore -
		int number = 99;
		System.out.println("-number = " + (-number));
		
//		operatore ++
		System.out.println("number = " + number);
		
		System.out.println("number++ = " + number++);
		System.out.println("number = " + number);
		
		System.out.println("++number = " + ++number);
		System.out.println("number = " + number);
		
//		operatore --
		System.out.println("number-- = " + number--);
		System.out.println("number = " + number);
		
		System.out.println("--number = " + --number);
		System.out.println("number = " + number);
		
		
	}

	private static void additionalOperators() {
		
//		casting per correggere una errata assegnazione
//		senza il casting il compilatore segnala errore
//		il casting forza la conversione a tipo, su un valore di un altro tipo
		int n = (int)1.0;
		short n2 = (short)123456;
		int n3 = (int)9l;
		long n4 = 123456789012l;
		
//		operatori += e -=
		
		int i = 3;
		i -= 1;
		System.out.println("i " + i );
		
		i += 1;
		System.out.println("i " + i );
		
//		operatori di confronto ( >, >=, <, <=)
		
		System.out.println("3 > 2 => " + (3 > 2));
		System.out.println("3 >= 2 => " + (3 >= 2));
		System.out.println("3 < 2 => " + (3 < 2));
		System.out.println("3 <= 2 => " + (3 <= 2));	
				
//		operatori di uguaglianza (== , !=)
		
		System.out.println("5 == 4 + 1 => " + (5 == 4 + 1));
		System.out.println("10 != 5 + 5 => " + (10 != 5 + 5));
		
//		operatori logici &&(and) e ||(or)
		boolean flag = true;
		String s = "ciao";
		System.out.println("flag vero e stringa valorizzata? " + (flag && s != null));
		
		System.out.println("flag falso o stringa nulla? " + (!flag || s == null));
		
		String s2 = "";
//		ottengo un errore se provo ad eseguire un'operazione su un'oggetto nullo
//		meglio controllare prima che non sia nullo
		System.out.println(s2.length());
		
//		operatore ternario
		
		Random random = new Random();
		int casuale = random.nextInt(100);
		System.out.println("casuale = " + casuale);
		System.out.println(casuale % 2 == 0 ? "pari" : "dispari"); // è praticamente un if su una sola riga
	}

}

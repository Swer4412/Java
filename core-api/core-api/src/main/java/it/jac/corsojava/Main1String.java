package it.jac.corsojava;

public class Main1String {

	public static void main(String[] args) {
		
		firstString();
		
		concatenation();
		
		immutability();
		
		stringPool();
		
		moreUsedMethods();
		
//		memoryProblems();
		
		usingStringBuilder();
		
		stringBuilderMoreUsedMethods();
		
		stringEquality();
		
	}

	private static void firstString() {
		
		String str = "windows 11";
		String str2 = new String("windows 11");
		
		System.out.println(str);
		
		System.out.println(str2);
	}
	
	private static void concatenation() {
		
//		l'operatore + esegue l'addizione se gli operandi sono numerici 
		System.out.println(1 + 2); // 3
		
//		l'operatore + esegue la concatenazione se gli operandi sono String
		System.out.println("a" + "b"); // ab
		
		System.out.println("a" + "b" + 3); // ab3
		System.out.println(1 + 2 + "c"); // 3c

//		caso particolare
		System.out.println("1" + "2" + 1 + 2); // 1212
		System.out.println("1" + "2" + (1 + 2)); // 123
		
		String s = "c";
		s += "i";
		s += "a";
		s += "o";
		
		System.out.println(s);
	}
	
	private static void immutability() {

		String s1 = "1";
		String s2 = s1.concat("2");
		String s3 = s2.concat("3");
//		quanto vale s2?
		System.out.println(s3);
	}
	
	private static void stringPool() {

//		per risparmiare spazio in memoria la JVM colleziona tutti i "literals" in una zona interna
		String s1 = "Jacobs vs Tortu";
		String s2 = "Jacobs vs Tortu";
//		esplicitando per� l'operatore "new" forziamo la JVM a dedicare una nuova area di memoria
		String s3 = new String("Jacobs vs Tortu");
		
		System.out.println("test pool1 => " + (s1 == s2));
		
		System.out.println("test pool2 => " + (s2 == s3));
		
		System.out.println("test equals => " + (s1.equals(s3)));
	}

	private static void moreUsedMethods() {

		String s1 = " testo di prova  ";
		
		System.out.println("La stringa � lunga: " + s1.length() + " caratteri");
		
		System.out.println("Il carattere in seconda posizione �: " + s1.charAt(1));
		
		System.out.println("stringa senza spazi in testa e coda: " + s1.trim());
		
		System.out.println("tutti i caratteri minuscoli " + s1.toLowerCase());
		
		System.out.println("tutti i caratteri maiuscoli " + s1.toUpperCase());
		
		System.out.println("primi 5 caratteri: " + s1.substring(0, 5));
		
		if (s1.startsWith("testo")) {
			System.out.println("la stringa inizio con testo");
		} else {
			System.out.println("la stringa non inizia con testo");
		}
		
		System.out.println("sostituisco 'o' con 'x': " + s1.replace('o', 'x'));
		
		String s2a = "testo ";
		String s2b = "di prova";
		String s3 = "testo di prova";
		
		if ((s2a + s2b) == s3) {
			System.out.println("1) le 2 stringhe sono uguali");
		} else {
			System.out.println("1) le 2 stringhe sono diverse");
		}
		
		if ((s2a + s2b).equals(s3)) {
			System.out.println("2) le 2 stringhe sono uguali");
		} else {			
			System.out.println("2) le 2 stringhe sono diverse");
		}
		
//		Remember that Strings are immutable and literals are pooled
//		es. if ("testo".equals("testo") {
//		...
//		}
	}
	
	private static void memoryProblems() {

		String s1 = "esempio di concatenazione di stringa: riga 1\n";
		int i = 0;
		do {
			
			s1 += "esempio di concatenazione di stringa: riga " + (i++) + "\n";
			
			if (i % 1000 == 0) {
				System.out.println("Attendo dopo riga " + i + ", lunghezza=" + s1.length());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			
		} while(s1.length() < 2_000_000_000);
	}

	private static void usingStringBuilder() {

		StringBuilder sb = new StringBuilder("esempio di concatenazione di stringa: riga 1\n");
		int i = 0;
		do {
			
			sb.append("esempio di concatenazione di stringa: riga " + (i++) + "\n");
			
			if (i % 1000 == 0) {
				try {
					System.out.println("Attendo dopo riga " + i + ", lunghezza=" + sb.length());
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
			
		} while(sb.length() < 2_000_000_000);
		
	}

	private static void stringBuilderMoreUsedMethods() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("stringa di test");
		
		System.out.println("Lunghezza della stringa: " + sb.length());
		
		sb.insert(0, "1-");
		
		System.out.println("Stringa modificata: " + sb);
		
		sb.reverse();
		
		System.out.println("Stringa al contrario: " + sb);

		sb.setLength(0);
		
		System.out.println("Stringa annullata: " + sb);
	
	}

	private static void stringEquality() {
		
		StringBuilder sb1 = new StringBuilder("rosso");
		StringBuilder sb2 = new StringBuilder("verde");
		StringBuilder sb3 = sb2.append("giallo");
		
		System.out.println("sb1 == sb2 => " + (sb1 == sb2));
		System.out.println("sb1 == sb3 => " + (sb1 == sb3));
		
		String s1 = "mouse";
		String s2 = "mouse";
		String s3 = "MOUSE".toLowerCase();
		
		System.out.println("s1 == s2 => " + (s1 == s2));
		System.out.println("s1 == s3 => " + (s1 == s3));
		
		s1 = new String("mouse");
		System.out.println("s1 == s2 => " + (s1 == s2));
		System.out.println("s1.equals(s2) => " + (s1.equals(s2)));
		
	}
}

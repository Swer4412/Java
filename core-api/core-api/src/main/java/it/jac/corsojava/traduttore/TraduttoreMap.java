package it.jac.corsojava.traduttore;

import java.util.HashMap; //Gli hashmap si può dire che siano simili ai dizionari di python
import java.util.Locale;
import java.util.Map;

public class TraduttoreMap implements Traduttore {
	//Per accedere ai dati di una mappa, in get() bisogna fornire una chiave e viene restituito il valore
	private Map<String, Map<String, String>> dizionariMap = new HashMap<>(); // Le mappe hanno una struttura chiave valore
	// La struttura così dichiarata è (francese : (bonjour: buongiorno)) simile a oggetti JSON
	{
		dizionariMap.put(Locale.ENGLISH.getLanguage(), new HashMap<>());
		dizionariMap.put(Locale.FRENCH.getLanguage(), new HashMap<>());
	}
	
	public void registraInglese(String vocaboloEn, String traduzione) {
		registraTraduzione(Locale.ENGLISH.getLanguage(), vocaboloEn, traduzione);
	}
	
	public void registraFrancese(String vocaboloFr, String traduzione) {
		registraTraduzione(Locale.FRENCH.getLanguage(), vocaboloFr, traduzione);
	}
	
	private void registraTraduzione(String language, String vocabolo, String traduzione) {
		
//		recupero il dizionario della lingua
		Map<String, String> dizionario = this.dizionariMap.get(language);
		if (!dizionario.containsKey(vocabolo)) {
			
			dizionario.put(vocabolo, traduzione);
			
		} else {
			System.out.println("Vocabolo gi� presente [" + vocabolo + "]");
		}
	}
	
	public String traduciInglese(String vocaboloEn) {
		
		return traduciVocabolo(Locale.ENGLISH.getLanguage(), vocaboloEn);
	}
	
	public String traduciFrancese(String vocaboloFr) {
		
		return traduciVocabolo(Locale.FRENCH.getLanguage(), vocaboloFr);
	}
	
	private String traduciVocabolo(String language, String vocabolo) {
		
//		recupero il dizionario della lingua
		Map<String, String> dizionario = this.dizionariMap.get(language);

		return dizionario.get(vocabolo);
	}
	
}

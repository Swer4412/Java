package it.jac.corsojava.traduttore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TraduttoreList implements Traduttore {

	private List<Item> dizionarioList = new ArrayList<>();
	
	@Override
	public void registraInglese(String vocaboloEn, String traduzione) {
		
		if (!containsVocabolo(Locale.ENGLISH, vocaboloEn)) {
			
			addVocabolo(Locale.ENGLISH, vocaboloEn, traduzione);
			
		} else {
			System.out.println("Vocabolo gi� presente [" + vocaboloEn + "]");
		}
	}
	
	@Override
	public void registraFrancese(String vocaboloFr, String traduzione) {
		
		if (!containsVocabolo(Locale.FRENCH, vocaboloFr)) {
			
			addVocabolo(Locale.FRENCH, vocaboloFr, traduzione);
			
		} else {
			System.out.println("Vocabolo gi� presente [" + vocaboloFr + "]");
		}
	}

	@Override
	public String traduciInglese(String vocaboloEn) {

		String result = null;
		int index = indexOfVocabolo(Locale.ENGLISH, vocaboloEn);
		if (index > -1) {
			result = this.dizionarioList.get(index).getTraduzione();
		}
		return result;
	}
	
	@Override
	public String traduciFrancese(String vocaboloFr) {
		
		String result = null;
		int index = indexOfVocabolo(Locale.FRENCH, vocaboloFr);
		if (index > -1) {
			result = this.dizionarioList.get(index).getTraduzione();
		}
		return result;
	}
	
	private void addVocabolo(Locale locale, String vocabolo, String traduzione) {
		
		Item item = new Item();
		item.setLocale(locale);
		item.setStrInLingua(vocabolo);
		item.setTraduzione(traduzione);
		dizionarioList.add(item);
	}

	private boolean containsVocabolo(Locale locale, String vocabolo) {

		return indexOfVocabolo(locale, vocabolo) > -1;
	}
	
	private int indexOfVocabolo(Locale locale, String vocabolo) {
		
		for(int i = 0;i < dizionarioList.size(); i++) {
			Item item = dizionarioList.get(i);
			if (item.getLocale().equals(locale)
					&& item.getStrInLingua().equals(vocabolo)) {
				return i;
			}
		}
		
		return -1;
	}
	
}

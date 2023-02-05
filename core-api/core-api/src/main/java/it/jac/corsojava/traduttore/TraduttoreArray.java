package it.jac.corsojava.traduttore;

import java.util.ArrayList;
import java.util.List;

public class TraduttoreArray implements Traduttore { //Implemento l'interfaccia Traduttore in modo che abbia la stessa forma degli altri

	private List<String[]> dizionarioEnIt = new ArrayList<>();
	private List<String[]> dizionarioFrIt = new ArrayList<>();
	
	@Override
	public void registraInglese(String vocaboloEn, String traduzione) {
		
		if (!containsVocabolo(dizionarioEnIt, vocaboloEn)) {
			
			addVocabolo(dizionarioEnIt, vocaboloEn, traduzione);
			
		} else {
			System.out.println("Vocabolo gi� presente [" + vocaboloEn + "]");
		}
	}
	
	@Override
	public void registraFrancese(String vocaboloFr, String traduzione) {
		
		if (!containsVocabolo(dizionarioFrIt, vocaboloFr)) {
			
			addVocabolo(dizionarioFrIt, vocaboloFr, traduzione);
			
		} else {
			System.out.println("Vocabolo gi� presente [" + vocaboloFr + "]");
		}
	}

	@Override
	public String traduciInglese(String vocaboloEn) {

		String result = null;
		int index = indexOfVocabolo(this.dizionarioEnIt, vocaboloEn);
		if (index > -1) {
			result = this.dizionarioEnIt.get(index)[1];
		}
		return result;
	}
	
	@Override
	public String traduciFrancese(String vocaboloFr) {
		
		String result = null;
		int index = indexOfVocabolo(this.dizionarioFrIt, vocaboloFr);
		if (index > -1) {
			result = this.dizionarioFrIt.get(index)[1];
		}
		return result;
	}
	
	private void addVocabolo(List<String[]> dizionario, String vocabolo, String traduzione) {
		
		dizionario.add(new String[] {vocabolo, traduzione});
	}

	private boolean containsVocabolo(List<String[]> dizionario, String vocabolo) {

		return indexOfVocabolo(dizionario, vocabolo) > -1;
	}
	
	private int indexOfVocabolo(List<String[]> dizionario, String vocabolo) {
		
		for(int i = 0;i < dizionario.size(); i++) {
			String[] item = dizionario.get(i);
			if (item[0].equals(vocabolo)) {
				return i;
			}
		}
		
		return -1;
	}
	
}

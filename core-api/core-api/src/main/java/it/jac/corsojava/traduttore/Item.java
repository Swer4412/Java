package it.jac.corsojava.traduttore;

import java.util.Locale; //Locale permette di memorizzare ulteriori informazioni della parola

public class Item {

	private String strInLingua;
	private String traduzione;
	private Locale locale;

	public String getStrInLingua() {
		return strInLingua;
	}

	public void setStrInLingua(String strInLingua) {
		this.strInLingua = strInLingua;
	}

	public String getTraduzione() {
		return traduzione;
	}

	public void setTraduzione(String traduzione) {
		this.traduzione = traduzione;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "Item [strInLingua=" + strInLingua + ", traduzione=" + traduzione + ", locale=" + locale + "]";
	}

}

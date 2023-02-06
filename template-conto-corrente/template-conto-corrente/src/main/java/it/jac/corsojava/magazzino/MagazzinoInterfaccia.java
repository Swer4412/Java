package it.jac.corsojava.magazzino;

public interface MagazzinoInterfaccia {

	public void aggiungiProdotto(String desc, String prezzo);
	public String cambiaStato(String codice, String stato);
	public String visualizzaProdotti();
}

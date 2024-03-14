package it.jac.springboot.apicomuni.entity;

public class Comune {
	private String codComune;
	private String codUnitaTerritoriale;
	private String progressivoComune;
	private String denItaExt;
	private String denIta;
	private String flagCapoluogo;
	
	public Comune () {
		this.codComune = "1";
		this.codUnitaTerritoriale = "2";
		this.progressivoComune = "3";
		this.denIta = "4";
		this.denItaExt = "5";
		this.flagCapoluogo = "6";
	}
	
	public String getCodComune() {
		return codComune;
	}
	public void setCodComune(String codComune) {
		this.codComune = codComune;
	}
	public String getCodUnitaTerritoriale() {
		return codUnitaTerritoriale;
	}
	public void setCodUnitaTerritoriale(String codUnitaTerritoriale) {
		this.codUnitaTerritoriale = codUnitaTerritoriale;
	}
	public String getProgressivoComune() {
		return progressivoComune;
	}
	public void setProgressivoComune(String progressivoComune) {
		this.progressivoComune = progressivoComune;
	}
	public String getDenItaExt() {
		return denItaExt;
	}
	public void setDenItaExt(String denItaExt) {
		this.denItaExt = denItaExt;
	}
	public String getDenIta() {
		return denIta;
	}
	public void setDenIta(String denIta) {
		this.denIta = denIta;
	}
	public String getFlagCapoluogo() {
		return flagCapoluogo;
	}
	public void setFlagCapoluogo(String flagCapoluogo) {
		this.flagCapoluogo = flagCapoluogo;
	}
	
	
	
}

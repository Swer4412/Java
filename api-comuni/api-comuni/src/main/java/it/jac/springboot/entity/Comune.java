package it.jac.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comune")
public class Comune {

	@Id
	@Column(name = "COD_COMUNE", nullable = false)
	private String codComune;
	
	@Column(name = "COD_UNITA_TERR", nullable = false)
	private String codUnitaTerr;
	
	@Column(name = "PROGRESSIVO_COMUNE", nullable = false)
	private String progressivoComune;
	
	@Column(name = "DEN_ITA_EXT", nullable = false)
	private String denItaExt;
	
	@Column(name = "DEN_ITA", nullable = false)
	private String denIta;
	
	@Column(name = "FLAG_CAPOLUOGO", nullable = false)
	private String flagCapoluogo;

	public Comune() {
		
		this.codComune = "1";
		this.codUnitaTerr = "19";
		this.progressivoComune = "001001";
		this.denItaExt = "Bergamo";
		this.denIta = "Bergamo";
		this.flagCapoluogo = "0";		
	}
	
	public String getCodComune() {
		return codComune;
	}

	public void setCodComune(String codComune) {
		this.codComune = codComune;
	}

	public String getCodUnitaTerr() {
		return codUnitaTerr;
	}

	public void setCodUnitaTerr(String codUnitaTerr) {
		this.codUnitaTerr = codUnitaTerr;
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

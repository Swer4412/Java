package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "noleggio")
public class SimulazioneEntity {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;
		
		@Column(name = "codice_conferma", nullable = false)
		private String codiceConferma;
		
		@Column(name = "categoria", nullable = false)
		private String categoria;
		
		@Column(name = "prezzo_giornaliero", nullable = false)
		private int prezzoGiornaliero;
		
		@Column(name = "data_ritiro", nullable = false)
		private LocalDateTime dataRitiro;
		
		@Column(name = "data_consegna", nullable = false)
		private LocalDateTime dataConsegna;
		
		@Column(name = "nominativo", nullable = false)
		private String nominativo;
		
		@Column(name = "codice_fiscale", nullable = false)
		private String codiceFiscale;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCodiceConferma() {
			return codiceConferma;
		}

		public void setCodiceConferma(String codiceConferma) {
			this.codiceConferma = codiceConferma;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public int getPrezzoGiornaliero() {
			return prezzoGiornaliero;
		}

		public void setPrezzoGiornaliero(int prezzoGiornaliero) {
			this.prezzoGiornaliero = prezzoGiornaliero;
		}

		public LocalDateTime getDataRitiro() {
			return dataRitiro;
		}

		public void setDataRitiro(LocalDateTime dataRitiro) {
			this.dataRitiro = dataRitiro;
		}

		public LocalDateTime getDataConsegna() {
			return dataConsegna;
		}

		public void setDataConsegna(LocalDateTime dataConsegna) {
			this.dataConsegna = dataConsegna;
		}

		public String getNominativo() {
			return nominativo;
		}

		public void setNominativo(String nominativo) {
			this.nominativo = nominativo;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}
		
		
		
		
}

package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;
		
		@Column(name = "data_visita", nullable = false)
		private LocalDateTime dataVisita;
		
		@Column(name = "ora_visita", nullable = false)
		private int oraVisita;
		
		@Column(name = "data_prenotazione", nullable = false)
		private LocalDateTime dataPrenotazione;
		
		@Column(name = "codice_conferma", nullable = false)
		private String codiceConferma;
		
		@ManyToOne
		@JoinColumn(name = "id_utente")
		private Utente utente;
		
		@ManyToOne
		@JoinColumn(name = "id_medico")
		private Medico medico;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public LocalDateTime getDataVisita() {
			return dataVisita;
		}

		public void setDataVisita(LocalDateTime dataVisita) {
			this.dataVisita = dataVisita;
		}

		public int getOraVisita() {
			return oraVisita;
		}

		public void setOraVisita(int oraVisita) {
			this.oraVisita = oraVisita;
		}

		public LocalDateTime getDataPrenotazione() {
			return dataPrenotazione;
		}

		public void setDataPrenotazione(LocalDateTime dataPrenotazione) {
			this.dataPrenotazione = dataPrenotazione;
		}

		public String getCodiceConferma() {
			return codiceConferma;
		}

		public void setCodiceConferma(String codiceConferma) {
			this.codiceConferma = codiceConferma;
		}

		public Utente getUtente() {
			return utente;
		}

		public void setUtente(Utente utente) {
			this.utente = utente;
		}

		public Medico getMedico() {
			return medico;
		}

		public void setMedico(Medico medico) {
			this.medico = medico;
		}
		
		
}

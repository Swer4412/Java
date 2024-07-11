package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;
		
		@Column(name = "nome", nullable = false)
		private String nome;
		
		@Column(name = "cognome", nullable = false)
		private String cognome;
		
		@Column(name = "email", nullable = false)
		private String email;
		
		@Column(name = "codice_fiscale", nullable = false)
		private double codiceFiscale;
		
		@Column(name = "data_nascita", nullable = false)
		private String dataNascita;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public double getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(double codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}

		public String getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(String dataNascita) {
			this.dataNascita = dataNascita;
		}
		
		
}

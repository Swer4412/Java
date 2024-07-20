package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private Long id;
		
		@Column(name = "nome", nullable = false)
		private String nome;
		
		@Column(name = "cognome", nullable = false)
		private String cognome;
		
		@Column(unique = true, nullable = false)
	    private String email;

		@Column(nullable = false)
	    private String password;

		@Column(name = "codice_fiscale", nullable = false)
		private String codiceFiscale;
		
		@Column(name = "data_nascita", nullable = false)
		private Date dataNascita;
		
		//One to many vuol dire che per ogni utente ci sono 0 o piú prenotazioni
		//Mapped specifica il nome del campo nell'altra entità (Prenotazione) che gestisce questa relazione
		@OneToMany(mappedBy = "utente") 
	    private List<Prenotazione> prenotazioni;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}

		public Date getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(Date dataNascita) {
			this.dataNascita = dataNascita;
		}

		public List<Prenotazione> getPrenotazioni() {
			return prenotazioni;
		}

		public void setPrenotazioni(List<Prenotazione> prenotazioni) {
			this.prenotazioni = prenotazioni;
		}
		
		
		
}

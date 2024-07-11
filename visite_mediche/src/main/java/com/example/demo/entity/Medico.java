package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medici")
public class Medico {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;
		
		@Column(name = "nome", nullable = false)
		private String nome;
		
		@Column(name = "cognome", nullable = false)
		private String cognome;
		
		@Column(name = "indirizzo_studio", nullable = false)
		private String indirizzoStudio;
		
		@Column(name = "prezzo_orario", nullable = false)
		private double prezzoOrario;
		
		@Column(name = "specialita", nullable = false)
		private String specialita;


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

		public String getIndirizzoStudio() {
			return indirizzoStudio;
		}

		public void setIndirizzoStudio(String indirizzoStudio) {
			this.indirizzoStudio = indirizzoStudio;
		}

		public double getPrezzoOrario() {
			return prezzoOrario;
		}

		public void setPrezzoOrario(double prezzoOrario) {
			this.prezzoOrario = prezzoOrario;
		}

		public String getSpecialita() {
			return specialita;
		}

		public void setSpecialita(String specialita) {
			this.specialita = specialita;
		}
		
		
		
}

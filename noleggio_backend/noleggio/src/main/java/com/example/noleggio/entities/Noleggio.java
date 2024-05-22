package com.example.noleggio.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "noleggio")
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODICE_CONFERMA", nullable = false)
    private String codiceConferma;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "PREZZO_GIORNALIERO", nullable = false)
    private Double prezzoGiornaliero;

    @Column(name = "DATA_RITIRO", nullable = false)
    private LocalDate dataRitiro;

    @Column(name = "DATA_CONSEGNA", nullable = false)
    private LocalDate dataConsegna;

    @Column(name = "NOMINATIVO", nullable = false)
    private String nominativo;

    @Column(name = "CODICE_FISCALE", nullable = false)
    private String codiceFiscale;

    @Column(name = "PREZZO_TOTALE", nullable = false)
    private Double prezzoTotale;

    public Noleggio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPrezzoGiornaliero() {
        return prezzoGiornaliero;
    }

    public void setPrezzoGiornaliero(Double prezzoGiornaliero) {
        this.prezzoGiornaliero = prezzoGiornaliero;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
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

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
}

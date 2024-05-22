package com.example.noleggio.dto;

public class NoleggioResponseDTO {

    private Long id;
    private String codiceConferma;

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
}

package com.example.noleggio.service;

import com.example.noleggio.dto.NoleggioRequestDTO;
import com.example.noleggio.dto.NoleggioResponseDTO;
import com.example.noleggio.entities.Noleggio;

import com.example.noleggio.repository.NoleggioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NoleggioService {

    private NoleggioRepository repository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public NoleggioService(NoleggioRepository repository) {

        this.repository = repository;
    }

    public List<Noleggio> findAll() {

//		se non vengono passati in input dei dati recupero solo i primi 10 elementi

        Page<Noleggio> page = this.repository.findAll(Pageable.ofSize(10).withPage(1));

        return page.getContent();
    }

    public NoleggioResponseDTO salvaNoleggio(NoleggioRequestDTO requestDTO) {

        Noleggio noleggio = new Noleggio();

        noleggio.setCategoria(requestDTO.getCategoria());
        noleggio.setNominativo(requestDTO.getNominativo());

        noleggio.setDataRitiro(LocalDate.parse(requestDTO.getDataInizio(), dateFormatter));
        noleggio.setDataConsegna(LocalDate.parse(requestDTO.getDataFine(), dateFormatter));

        noleggio.setCodiceFiscale(requestDTO.getCodiceFiscale());

        // Calcolo del prezzo giornaliero in base alla categoria
        Double prezzoGiornaliero = calcolaPrezzoGiornaliero(requestDTO.getCategoria());
        noleggio.setPrezzoGiornaliero(prezzoGiornaliero);

        // Calcolo del numero di giorni di noleggio
        long giorniNoleggio = ChronoUnit.DAYS.between(
                LocalDate.parse(requestDTO.getDataInizio(), dateFormatter),
                LocalDate.parse(requestDTO.getDataFine(), dateFormatter)
        );

        // Calcolo del prezzo totale
        double prezzoTotale = prezzoGiornaliero * giorniNoleggio;
        noleggio.setPrezzoTotale(prezzoTotale);

        // Genera un codice di conferma (può essere personalizzato)
        noleggio.setCodiceConferma(generaCodiceConferma());

        // Salva il noleggio nel repository
        Noleggio noleggioSalvato = repository.save(noleggio);

        // Converte l'entità salvata in un DTO di risposta
        return convertToResponseDTO(noleggioSalvato);
    }

    private Double calcolaPrezzoGiornaliero(String categoria) {
        return switch (categoria) {
            case "CAT-A" -> 120.0; // Prezzo per CAT-A
            case "CAT-B" -> 180.0; // Prezzo per CAT-B
            case "CAT-C" -> 210.0; // Prezzo per CAT-C
            default -> throw new IllegalArgumentException("Categoria non valida");
        };
    }

    private String generaCodiceConferma() {
        // Logica per generare un codice di conferma unico
        return "CONF-" + System.currentTimeMillis(); // Esempio semplice
    }

    private NoleggioResponseDTO convertToResponseDTO(Noleggio noleggio) {
        NoleggioResponseDTO responseDTO = new NoleggioResponseDTO();
        responseDTO.setId(noleggio.getId());
        responseDTO.setCodiceConferma(noleggio.getCodiceConferma());
        return responseDTO;
    }

}

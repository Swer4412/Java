package com.example.demo.controller;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Medico;
import com.example.demo.entity.Prenotazione;
import com.example.demo.service.AppService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/visite")
public class AppController {
    private final AppService service;
    
    public AppController(AppService service) {
        this.service = service;
    }
    
    @PostMapping("/prenota")
    public String savePrenotazione(@RequestBody Prenotazione prenotazione, Authentication authentication, HttpServletResponse response) {
        //Qui vengono effettuati i controlli sulla richiesta fatta
    	if (prenotazione.getOraVisita() > 18 || prenotazione.getOraVisita() < 8) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "";
        }
        
    	//Ottengo l'email dal token e salvo quella nella prenotazione
        String email = authentication.getName();
        prenotazione.getUtente().setEmail(email);
        
        this.service.savePrenotazione(prenotazione);
        
        return prenotazione.getCodiceConferma();
    }
    	
    @GetMapping("/prenotazioni")
    public List<Prenotazione> findPrenotazioniByEmailUtente(Authentication authentication) {
    	//Ottengo l'email dal corpo del token
        String email = authentication.getName();
        return this.service.findPrenotazioniByEmailUtente(email);
    }
    
    @GetMapping("/medici")
    public List<Medico> findAllMedici() {
    	//Non serve qui ottenere l'email dal jwt dato che i dati sono uguali per tutti
        return this.service.findAllMedici();
    }
}
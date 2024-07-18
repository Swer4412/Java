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
        if (prenotazione.getOraVisita() > 18 || prenotazione.getOraVisita() < 8) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "";
        }
        
        String email = authentication.getName();
        prenotazione.getUtente().setEmail(email);
        
        this.service.savePrenotazione(prenotazione);
        
        return prenotazione.getCodiceConferma();
    }
    
    @GetMapping("/prenotazioni")
    public List<Prenotazione> findPrenotazioniByEmailUtente(Authentication authentication) {
        String email = authentication.getName();
        return this.service.findPrenotazioniByEmailUtente(email);
    }
    
    @GetMapping("/medici")
    public List<Medico> findAllMedici() {
        return this.service.findAllMedici();
    }
}
package com.example.noleggio.controller;

import com.example.noleggio.dto.NoleggioRequestDTO;
import com.example.noleggio.dto.NoleggioResponseDTO;

import com.example.noleggio.service.NoleggioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noleggio")
@CrossOrigin(origins = "http://localhost:4200")
public class NoleggioController {

    private static final Logger log = LogManager.getLogger(NoleggioController.class);
    private final NoleggioService service;

    public NoleggioController(NoleggioService service) {

        this.service = service;
    }

    @PostMapping("/salva")
    public NoleggioResponseDTO saveNoleggio(@RequestBody NoleggioRequestDTO requestDTO) {
        log.info("richiamato metodo POST saveNoleggio");
        return service.salvaNoleggio(requestDTO);
    }

}

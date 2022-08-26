package com.integrador_info.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.integrador_info.integrador.domain.Fuente;
import com.integrador_info.integrador.repository.FuenteRepository;

@RestController
public class FuenteController {
    private final FuenteRepository fuenteRepository;

    @Autowired
    public FuenteController(FuenteRepository fuenteRepository){
        this.fuenteRepository = fuenteRepository;
    }

    @PostMapping("/source")
    public Fuente createFuente(@RequestBody Fuente fuente){
        return fuenteRepository.save(fuente);
    }
}

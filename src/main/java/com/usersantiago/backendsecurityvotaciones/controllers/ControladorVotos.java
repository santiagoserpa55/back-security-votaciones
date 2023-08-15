package com.usersantiago.backendsecurityvotaciones.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersantiago.backendsecurityvotaciones.services.VotosService;
@CrossOrigin

@RestController
@RequestMapping("/resultados")
public class ControladorVotos {
  
  private final VotosService votosService;
  
  public ControladorVotos(VotosService votosService) {
    this.votosService = votosService;
  }
  
  @GetMapping
  public ResponseEntity<Object> getResultados() {
    return this.votosService.getAllResultados();
  }
  
  

}

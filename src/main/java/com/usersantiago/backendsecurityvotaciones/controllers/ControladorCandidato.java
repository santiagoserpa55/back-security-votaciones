package com.usersantiago.backendsecurityvotaciones.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersantiago.backendsecurityvotaciones.models.Candidato;
import com.usersantiago.backendsecurityvotaciones.services.CandidatoService;

@RestController
@RequestMapping("/candidatos")
public class ControladorCandidato {
  
  private final CandidatoService candidatoService;
  
  public ControladorCandidato(CandidatoService candidatoService) {
    this.candidatoService = candidatoService;
  }
  
  @GetMapping
  public List<Candidato> getCandidatos() {
    return this.candidatoService.getAllCandidatos();
  }
  
  @PostMapping
  public ResponseEntity<Object> createCandidate(@RequestBody Candidato candidato) {
    return this.candidatoService.registrarCandidato(candidato);
  }

}

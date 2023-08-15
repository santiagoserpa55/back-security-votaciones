package com.usersantiago.backendsecurityvotaciones.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersantiago.backendsecurityvotaciones.models.Votacion;
import com.usersantiago.backendsecurityvotaciones.services.VotacionService;
@CrossOrigin

@RestController
@RequestMapping("/votar")
public class ControladorVotacion {
  
  private final VotacionService votacionService;
  
  public ControladorVotacion(VotacionService votacionService) {
    this.votacionService = votacionService;
  }
  
  @PostMapping
  public ResponseEntity<Object> votar(@RequestBody Votacion votacion) {
    return this.votacionService.vote(votacion);    
  }

}

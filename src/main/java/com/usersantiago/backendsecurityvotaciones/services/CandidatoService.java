package com.usersantiago.backendsecurityvotaciones.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usersantiago.backendsecurityvotaciones.models.Candidato;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioCandidato;

@Service
public class CandidatoService {
  HashMap<String, Object> datos;
  private final RepositorioCandidato repositorioCandidato;

  public CandidatoService(RepositorioCandidato repositorioCandidato) {
    this.repositorioCandidato = repositorioCandidato;
  }

  public ResponseEntity<Object> registrarCandidato(Candidato candidato) {
    datos = new HashMap<>();
    Optional<Candidato> existeCandidatoByCedula = this.repositorioCandidato
        .findCandidatoByCedula(candidato.getCedula());
    if (existeCandidatoByCedula.isPresent()) {
      datos.put("message", "Ya existe un candidato con esa cedula");
      return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
    }
    System.out.println(candidato);
    this.repositorioCandidato.save(candidato);
    datos.put("success", candidato);
    return new ResponseEntity<Object>(datos, HttpStatus.CREATED);
  }

  public List<Candidato> getAllCandidatos() {
    return this.repositorioCandidato.findAll();
  }

}

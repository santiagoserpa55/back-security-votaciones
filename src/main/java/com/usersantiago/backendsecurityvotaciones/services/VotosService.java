package com.usersantiago.backendsecurityvotaciones.services;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioVotos;

@Service
public class VotosService {
  HashMap<String, Object> datos;
  
  private final RepositorioVotos repositorioVotos;
  
  public VotosService(RepositorioVotos repositorioVotos) {
    this.repositorioVotos = repositorioVotos;
  }
  
  public ResponseEntity<Object> getAllResultados() {
    datos = new HashMap<>();
    var votos = this.repositorioVotos.countAll();
    if (votos.isEmpty()) {
      datos.put("message", "No hay resultados");
      return new ResponseEntity<Object>(datos, HttpStatus.NO_CONTENT);
    }
    datos.put("resultados", votos);
    return new ResponseEntity<Object>(datos, HttpStatus.FOUND);
  }
  
}

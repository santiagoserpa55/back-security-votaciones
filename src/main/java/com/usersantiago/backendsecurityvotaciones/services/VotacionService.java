package com.usersantiago.backendsecurityvotaciones.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usersantiago.backendsecurityvotaciones.models.Votacion;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioVotacion;

@Service
public class VotacionService {
  HashMap<String, Object> datos;
  
  private final RepositorioVotacion repositorioVotacion;
  
  public VotacionService(RepositorioVotacion repositorioVotacion) {
    this.repositorioVotacion = repositorioVotacion;
  }
  
  public ResponseEntity<Object> vote(Votacion votacion) {
    datos = new HashMap<>();
    Optional<Votacion> yaVoto = this.repositorioVotacion.findVoteByUsuario(votacion.getUsuario());
    if (yaVoto.isPresent()) {
      datos.put("message", "El usuario ya Voto");
      return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
    }
    this.repositorioVotacion.save(votacion);
    datos.put("success", "Votacion exitosa");
    return new ResponseEntity<Object>(datos, HttpStatus.CREATED);
    
    
  }

}

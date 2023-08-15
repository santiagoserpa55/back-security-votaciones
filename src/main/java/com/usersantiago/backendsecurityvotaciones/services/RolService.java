package com.usersantiago.backendsecurityvotaciones.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usersantiago.backendsecurityvotaciones.models.Rol;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioRol;

@Service
public class RolService {
  HashMap<String, Object> datos;
  private final RepositorioRol repositorioRol;

  public RolService(RepositorioRol repositorioRol) {
    this.repositorioRol = repositorioRol;
  }

  public ResponseEntity<Object> listRoles() {
    datos = new HashMap<>();
    datos.put("data", this.repositorioRol.findAll());
    return new ResponseEntity<Object>(datos, HttpStatus.FOUND);
  }
  

  public ResponseEntity<Object> createRol(Rol infoRol) {
    datos = new HashMap<>();
    Optional<Rol> existeRol = this.repositorioRol.findRolByNombre(infoRol.getNombre());
    if (existeRol.isPresent()) {
      datos.put("error", "Ya existe un rol con ese nombre");
      return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
    }
    this.repositorioRol.save(infoRol);
    datos.put("success", "Registro exitoso");
    return new ResponseEntity<Object>(datos, HttpStatus.CREATED);
  }

}

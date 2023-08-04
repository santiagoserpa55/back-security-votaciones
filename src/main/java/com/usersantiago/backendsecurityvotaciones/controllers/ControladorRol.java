package com.usersantiago.backendsecurityvotaciones.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.usersantiago.backendsecurityvotaciones.models.Rol;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioRol;
import com.usersantiago.backendsecurityvotaciones.services.RolService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class ControladorRol {

  private final RolService rolService;

  public ControladorRol(RolService rolService) {
    this.rolService = rolService;
  }

  @GetMapping("")
  public ResponseEntity<Object> index() {
    return this.rolService.listRoles();
  }

  @PostMapping
  public ResponseEntity<Object> createRol(@RequestBody Rol infoRol) {
    return this.rolService.createRol(infoRol);
  }

//  @GetMapping("{id}")
//  public Rol show(@PathVariable String id) {
//    Rol rolActual = this.repositorioRol.findById(id).orElse(null);
//    return rolActual;
//  }
//
//  @PutMapping("{id}")
//  public Rol update(@PathVariable String id, @RequestBody Rol infoRol) {
//    Rol rolActual = this.repositorioRol.findById(id).orElse(null);
//    if (rolActual != null) {
//      rolActual.setNombre(infoRol.getNombre());
//      return this.repositorioRol.save(rolActual);
//    } else {
//      return null;
//    }
//  }
//
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  @DeleteMapping("{id}")
//  public void delete(@PathVariable String id) {
//    Rol rolActual = this.repositorioRol.findById(id).orElse(null);
//    if (rolActual != null) {
//      this.repositorioRol.delete(rolActual);
//    }
//  }
}

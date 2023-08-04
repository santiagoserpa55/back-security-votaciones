package com.usersantiago.backendsecurityvotaciones.repositories;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usersantiago.backendsecurityvotaciones.models.Rol;



public interface RepositorioRol extends MongoRepository<Rol,String> {
  Optional<Rol> findRolByNombre();
    
}

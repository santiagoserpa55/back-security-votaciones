package com.usersantiago.backendsecurityvotaciones.repositories;


import org.springframework.data.mongodb.repository.Query;

import com.usersantiago.backendsecurityvotaciones.models.Usuario;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {
    @Query("{'correo': ?0}")
    Optional<Usuario> getUserByEmail(String correo);
    Optional<Usuario> findUsuarioByCedula(String cedula);
    
}


package com.usersantiago.backendsecurityvotaciones.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usersantiago.backendsecurityvotaciones.models.Candidato;

public interface RepositorioCandidato extends MongoRepository<Candidato, String> {
  Optional<Candidato> findCandidatoByCedula(String cedula); 
}

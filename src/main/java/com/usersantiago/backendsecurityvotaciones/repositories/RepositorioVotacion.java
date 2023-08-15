package com.usersantiago.backendsecurityvotaciones.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.usersantiago.backendsecurityvotaciones.models.Usuario;
import com.usersantiago.backendsecurityvotaciones.models.Votacion;

public interface RepositorioVotacion extends MongoRepository<Votacion, String> {
  Optional<Votacion> findVoteByUsuario(Usuario usuario);
  
  // Define la consulta de agregación
  @Aggregation("{ $group: { _id: \"$candidato\", conteo: { $sum: 1 } } }")
  List<Object> countAll();
  
}

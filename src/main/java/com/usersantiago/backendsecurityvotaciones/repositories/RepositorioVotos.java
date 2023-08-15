package com.usersantiago.backendsecurityvotaciones.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.usersantiago.backendsecurityvotaciones.models.Votacion;
import com.usersantiago.backendsecurityvotaciones.models.Votos;

public interface RepositorioVotos extends MongoRepository<Votacion, Long> {
  @Aggregation(pipeline = {"{$group: {_id: \"$candidato\", conteo: {$sum: 1}}}",
      "{$project: {_id: 0, candidato: \"$_id\", conteo: 1}}"
  })
  List<Votos> countAll();
}

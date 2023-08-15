package com.usersantiago.backendsecurityvotaciones.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Votos {
 
  @DBRef
  private Candidato candidato;
  private Long conteo;
  
  
  public Votos(Candidato candidato, Long conteo) {
    super();
    this.candidato = candidato;
    this.conteo = conteo;
  }


  public Votos() {
    super();
  }
  
  
  
}

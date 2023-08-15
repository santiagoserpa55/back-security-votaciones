package com.usersantiago.backendsecurityvotaciones.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document
public class Candidato {
  
  @Id
  private String _id;
  private String cedula;
  private String eslogan;
  private String partido;
  @DBRef
  private Usuario usuario;
  
  public Candidato(String cedula, String eslogan, String partido, Usuario usuario) {
    super();
    this.cedula = cedula;
    this.eslogan = eslogan;
    this.partido = partido;
    this.usuario = usuario;
  }
  
}

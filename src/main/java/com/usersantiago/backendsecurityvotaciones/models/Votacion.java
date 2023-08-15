package com.usersantiago.backendsecurityvotaciones.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Votacion {

  private String _id;
  @DBRef
  private Usuario usuario;
  @DBRef
  private Candidato candidato;
  private int conteo;

  public Votacion() {
    super();
  }

  public Votacion(Usuario usuario, Candidato candidato, int conteo) {
    super();
    this.usuario = usuario;
    this.candidato = candidato;
    this.conteo = conteo;
  }

}

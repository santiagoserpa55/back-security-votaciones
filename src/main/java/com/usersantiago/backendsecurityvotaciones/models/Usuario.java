package com.usersantiago.backendsecurityvotaciones.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Usuario {
  @Id
  private String _id;
  private String cedula;
  private String nombres;
  private String apellidos;
  private String correo;
  private String contrasena;
  @DBRef
  private Rol rol;

  public Usuario(String cedula, String nombres, String apellidos, String correo, String contrasena, Rol rol) {
    super();
    this.cedula = cedula;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.correo = correo;
    this.contrasena = contrasena;
    this.rol = rol;
  }

}

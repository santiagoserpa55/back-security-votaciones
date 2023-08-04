package com.usersantiago.backendsecurityvotaciones.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usersantiago.backendsecurityvotaciones.models.Usuario;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioUsuario;

@Service
public class UsuarioService {
  private final RepositorioUsuario repositorioUsuario;
  HashMap<String, Object> datos;

  public UsuarioService(RepositorioUsuario repositorioUsuario) {
    this.repositorioUsuario = repositorioUsuario;
  }

  public ResponseEntity<Object> listUsers() {
    datos = new HashMap<>();
    datos.put("data", repositorioUsuario.findAll());
    return new ResponseEntity<Object>(datos, HttpStatus.FOUND);
  }

  public ResponseEntity<Object> createUser(Usuario newUser) {
    datos = new HashMap<>();
    Optional<Usuario> existeEmail = this.repositorioUsuario.getUserByEmail(newUser.getCorreo());
    if (existeEmail.isPresent()) {
      //datos.put("error", true);
      datos.put("message", "EL correo proporcionado ya esta en uso");
      return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
    }
    //datos.put("data", newUser);
    datos.put("success", "Registro exitoso");
    newUser.setContrasena(convertirSHA256(newUser.getContrasena()));
    this.repositorioUsuario.save(newUser);
    return new ResponseEntity<Object>(datos, HttpStatus.CREATED);
  }

  public ResponseEntity<Object> actalizarUsuario(String usuarioId, Usuario usuarioActualizado) {
    datos = new HashMap<>();
    Optional<Usuario> existeUsuarioById = this.repositorioUsuario.findById(usuarioId);
    Optional<Usuario> existeUsuarioByCedula = this.repositorioUsuario
        .findUsuarioByCedula(usuarioActualizado.getCedula());
    Optional<Usuario> existeUsuarioByCorreo = this.repositorioUsuario.getUserByEmail(usuarioActualizado.getCorreo());
    Usuario usuario = existeUsuarioById.get();

    if (!existeUsuarioById.isPresent()) {
      datos.put("error", "No existe un usuario con ese id");
      return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
    }

    if (usuarioActualizado.getCedula() != null && !usuarioActualizado.getCedula().equals(usuario.getCedula())) {
      if (existeUsuarioByCedula.isPresent()) {
        datos.put("error", "La cedula proporcionada ya existe");
        return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
      }
      usuario.setCedula(usuarioActualizado.getCedula());
    }

    if (usuarioActualizado.getCorreo() != null && !usuarioActualizado.getCorreo().equals(usuario.getCorreo())) {
      if (existeUsuarioByCorreo.isPresent()) {
        datos.put("error", "El correo proporcionado ya existe");
        return new ResponseEntity<Object>(datos, HttpStatus.CONFLICT);
      }
      usuario.setCorreo(usuarioActualizado.getCorreo());
    }
    usuario.setNombres(usuarioActualizado.getNombres());
    usuario.setApellidos(usuarioActualizado.getApellidos());
    usuario.setRol(usuarioActualizado.getRol());
    this.repositorioUsuario.save(usuario);
    datos.put("message", "Usuario actualizado exitosamente");
    return new ResponseEntity<Object>(datos, HttpStatus.ACCEPTED);
  }

  public ResponseEntity<Object> validarUsuario(Usuario userToValidate) {
    datos = new HashMap<>();
    Optional<Usuario> existeByCorreo = this.repositorioUsuario.getUserByEmail(userToValidate.getCorreo());
    if (!existeByCorreo.isPresent()) {
      datos.put("error", "El correo proporcionado no se encuentra registrado");
      return new ResponseEntity<Object>(datos, HttpStatus.NOT_FOUND);
    }
    Usuario usuario = existeByCorreo.get();
    if (usuario != null && usuario.getContrasena().equals(convertirSHA256(userToValidate.getContrasena()))) {
      usuario.setContrasena("");
      datos.put("Acceso autorizado", usuario);
      return new ResponseEntity<Object>(datos, HttpStatus.ACCEPTED);
    } else {
      datos.put("Acceso no autorizado", "Credenciales invalidas");
    }
    return new ResponseEntity<Object>(datos, HttpStatus.UNAUTHORIZED);
  }

  public ResponseEntity<Object> eliminarUsuario(String id) {
    datos = new HashMap<>();
    Optional<Usuario> usuarioEncontrado = this.repositorioUsuario.findById(id);
    if (!usuarioEncontrado.isPresent()) {
      datos.put("message", "El usuario no existe");
      return new ResponseEntity<Object>(datos, HttpStatus.NOT_FOUND);
    }
    this.repositorioUsuario.deleteById(id);
    datos.put("message", "Usuario eliminado");
    return new ResponseEntity<Object>(datos, HttpStatus.ACCEPTED);
  }

  public String convertirSHA256(String password) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    byte[] hash = md.digest(password.getBytes());
    StringBuffer sb = new StringBuffer();
    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

}

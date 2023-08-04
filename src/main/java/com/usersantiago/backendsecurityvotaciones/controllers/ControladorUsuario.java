package com.usersantiago.backendsecurityvotaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.usersantiago.backendsecurityvotaciones.models.Rol;
import com.usersantiago.backendsecurityvotaciones.models.Usuario;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioRol;
import com.usersantiago.backendsecurityvotaciones.repositories.RepositorioUsuario;
import com.usersantiago.backendsecurityvotaciones.services.UsuarioService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

  private final UsuarioService usuarioService;

  @Autowired
  private final RepositorioUsuario miRepositorioUsuario;
  @Autowired
  private final RepositorioRol miRepositorioRol;

  public ControladorUsuario(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
    this.miRepositorioUsuario = null;
    this.miRepositorioRol = null;
  }

  @GetMapping("")
  public ResponseEntity<Object> index() {
    return this.usuarioService.listUsers();
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody Usuario newUser) {
    return this.usuarioService.createUser(newUser);
  }

  @PostMapping("/validar")
  public ResponseEntity<Object> validarUsuario(@RequestBody Usuario userToValidate) {
    return this.usuarioService.validarUsuario(userToValidate);
  }

//    @GetMapping("{id}")
//    public Usuario show(@PathVariable String id){
//        Usuario usuarioActual=this.miRepositorioUsuario.findById(id).orElse(null);
//        return usuarioActual;
//    }

//    @PutMapping("{id}")
//    public Usuario update(@PathVariable String id,@RequestBody  Usuario infoUsuario){
//        Usuario usuarioActual=this.miRepositorioUsuario.findById(id).orElse(null);
//        if (usuarioActual!=null){
//            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
//            usuarioActual.setCorreo(infoUsuario.getCorreo());
//            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
//            return this.miRepositorioUsuario.save(usuarioActual);
//        }else{
//            return null;
//        }
//    }

  @PutMapping("{usuarioId}")
  public ResponseEntity<Object> actualizarUsuario(@PathVariable("usuarioId") String usuarioId,
      @RequestBody Usuario usuarioActualizado) {
    return this.usuarioService.actalizarUsuario(usuarioId, usuarioActualizado);
  }

  @DeleteMapping("{usuarioId}")
  public ResponseEntity<Object> eliminarUsuario(@PathVariable("usuarioId") String id) {
    return this.usuarioService.eliminarUsuario(id);
  }

  /**
   * Relación (1 a n) entre rol y usuario
   * 
   * @param id
   * @param id_rol
   * @return
   */
  @PutMapping("{id}/rol/{id_rol}")
  public Usuario asignarRolAUsuario(@PathVariable String id, @PathVariable String id_rol) {
    Usuario usuarioActual = this.miRepositorioUsuario.findById(id).orElse(null);
    Rol rolActual = this.miRepositorioRol.findById(id_rol).orElse(null);
    if (usuarioActual != null && rolActual != null) {
      usuarioActual.setRol(rolActual);
      return this.miRepositorioUsuario.save(usuarioActual);
    } else {
      return null;
    }

  }

}

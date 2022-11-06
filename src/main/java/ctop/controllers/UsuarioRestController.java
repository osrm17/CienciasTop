package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Usuario;
import ctop.model.service.UsuarioServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los usuarios.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private UsuarioServiceInterface usuarioService;

    @GetMapping("/usuarios")
    public Iterable<Usuario> index() {
        return usuarioService.findAll();
    }

}

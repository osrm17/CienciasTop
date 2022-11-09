package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Usuario;
import ctop.model.service.ServiceInterface;

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
    private ServiceInterface<Usuario, String> usuarioService;

    @GetMapping("/usuarios")
    public Iterable<Usuario> index() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/{numct}")
    public Usuario show(@PathVariable String numct) {
        return usuarioService.findById(numct);
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/usuarios/{numct}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario nuevo, @PathVariable String numct) {
        Usuario actual = usuarioService.findById(numct);
        actual.setNumct(nuevo.getNumct());
        actual.setContrasenia(nuevo.getContrasenia());
        actual.setNombre(nuevo.getNombre());
        actual.setPaterno(nuevo.getPaterno());
        actual.setMaterno(nuevo.getMaterno());
        actual.setEstaActivo(nuevo.isEstaActivo());
        actual.setCorreo(nuevo.getCorreo());
        actual.setCelular(nuevo.getCelular());
        actual.setPumaPuntos(nuevo.getPumaPuntos());
        actual.setEsProveedor(nuevo.isEsProveedor());
        actual.setEsAdministrador(nuevo.isEsAdministrador());
        usuarioService.save(actual);
        return actual;
    }

    @DeleteMapping("/usuarios/{numct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String numct) {
        usuarioService.delete(numct);
    }

}

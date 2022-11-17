package ctop.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> show(@PathVariable String numct) {
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();
        // Error con el servidor o base de datos.
        try {
            usuario = usuarioService.findById(numct);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta del usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Error con el id ingresado.
        if(usuario == null) {
            response.put("mensaje", "¡El usuario ".concat(numct.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }


    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            if (null != usuarioService.findById(usuario.getNumct())) {
                response.put("mensaje", "Error: llave duplicada.");
                response.put("error", "Llave duplicada en los usuarios.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            usuarioNuevo = usuarioService.save(usuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al agregar al usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El usuario ha sido agregado con éxito!");
        response.put("producto", usuarioNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{numct}")
    public ResponseEntity<?> update(@RequestBody Usuario nuevo, @PathVariable String numct) {
        Usuario actual = this.usuarioService.findById(numct);
        Usuario usuarioUpdate = null;
        Map<String,Object> response = new HashMap<>();
        //Error con el id ingresado.
        if(actual == null) {
            response.put("mensaje", "Error: no se puede editar al usuario ".concat(numct.toString().concat(" porque no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
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
            usuarioUpdate = usuarioService.save(actual);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al actualizar al usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El usuario ha sido actualizado con éxito!");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }


    @DeleteMapping("/usuarios/{numct}")
    public ResponseEntity<?> delete(@PathVariable String numct) {
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioService.delete(numct);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al eliminar al usuario de la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El usuario ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }
    
    
    @PutMapping("/usuarios/sumar/{numct}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario sumar(@RequestBody Usuario nuevo, @PathVariable String numct){
    	int total = 0;
    	Usuario usuario = usuarioService.findById(numct);
    	if(usuario == null)
    		throw new RuntimeException("Usuario no existe.");
    	if(usuario.getPumaPuntos() == 500)
    		throw new RuntimeException("Haz alcanzado el máximo número de Puma Puntos.");

    	total = usuario.getPumaPuntos() + nuevo.getPumaPuntos();
    	if(total>500) {
    		total = 500;
    		usuario.setPumaPuntos(total);
    		usuarioService.save(usuario);
    		return usuario;
    	}
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	return usuario;
    }
    
    @PutMapping("/usuarios/restar/{numct}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario restar(@RequestBody Usuario nuevo, @PathVariable String numct){
    	Usuario usuario = usuarioService.findById(numct);
    	int total;
    	if(usuario == null)
    		throw new RuntimeException("Usuario no existe.");
    	if(usuario.getPumaPuntos() == 0) 
    		throw new RuntimeException( "Tienes 0 PumaPuntos disponibles.");
    	if(nuevo.getPumaPuntos()>usuario.getPumaPuntos())
    		throw new RuntimeException( "No tienes suficientes PumaPuntos");
    	total = usuario.getPumaPuntos() - nuevo.getPumaPuntos();
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	return usuario;
    }
}

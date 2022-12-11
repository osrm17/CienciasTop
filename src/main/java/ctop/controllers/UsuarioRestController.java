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
    public ResponseEntity<?> sumar(@RequestBody Usuario nuevo, @PathVariable String numct){
    	Map<String,Object>response = new HashMap<>();
    	int total = 0;
    	Usuario usuario = usuarioService.findById(numct);
    	//Determina si existe el usuario
    	if(usuario == null) {
    		response.put("mensaje", "El usuario " +  numct + " no existe.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    	}
    		
    	if(usuario.getPumaPuntos() == 500) {
    		response.put("mensaje", "Haz alcanzado el máximo número de Pumapuntos.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    	}
    	//Se hace la suma	
    	total = usuario.getPumaPuntos() + nuevo.getPumaPuntos();
    	if(total>500) {
    		int sumados = 500 - usuario.getPumaPuntos();
    		usuario.setPumaPuntos(500);
    		usuarioService.save(usuario);
    		response.put("mensaje", "Sólo se sumaron " + sumados + " Pumapuntos.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    	}
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	response.put("mensaje", "Se añadieron " + nuevo.getPumaPuntos() + " Pumapuntos.");
    	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/usuarios/restar/{numct}")
    public ResponseEntity<?> restar(@RequestBody Usuario nuevo, @PathVariable String numct){
    	Map<String, Object>response = new HashMap<>();
    	Usuario usuario = usuarioService.findById(numct);
    	int total;
    	
    	if(usuario == null) {
    		response.put("mensaje", "El usuario " +  numct + " no existe.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    	}

    	if(usuario.getPumaPuntos() == 0) {
    		response.put("mensaje", "El usuario " + numct + " tiene 0 Pumapuntos.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    	}
    	
    	if(nuevo.getPumaPuntos()>usuario.getPumaPuntos()) {
    		response.put("mensaje", "El usuario " + numct + " no tiene suficientes Pumapuntos.");
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    	}
    	total = usuario.getPumaPuntos() - nuevo.getPumaPuntos();
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	response.put("mensaje", "Se restaron " + nuevo.getPumaPuntos() + " Pumapuntos.");
    	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}

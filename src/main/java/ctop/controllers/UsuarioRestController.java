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

import ctop.model.entity.AdministrarPuntos;
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
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = null;
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioNuevo = usuarioService.save(usuario);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al agregar al usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El usuario ha sido agregado con éxito!");
        response.put("usuario", usuarioNuevo);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
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
    
    
    @PutMapping("/usuarios/suma1/{numct}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario sumaPuntos(@PathVariable String numct, @RequestBody AdministrarPuntos admin) {
    	String mensaje = "";
		Integer total = 0;
    	Usuario usuario = usuarioService.findById(numct);
    	if(usuario == null) {
    		mensaje = "Usuario no registrado.";
    		return null;
    	}
    	if(usuario.getPumaPuntos() == 500) {
    		mensaje = "Ya has alcanzado el máximo número de PumaPuntos.";
        	return null;
    	}
    	total = usuario.getPumaPuntos() + admin.getPuntos(); //Suponiendo que los puntos que se pasen sea un parámetro válido.
    	if(total > 500) { //Se mochan los puntos extras.
    		total = 500;
    		usuario.setPumaPuntos(total);
        	usuarioService.save(usuario);
    		return usuario;
    	}
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	return usuario;
    }
    
    
    @PutMapping("/usuarios/resta/{numct}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario restaPuntos(@PathVariable String numct, @RequestBody AdministrarPuntos admin) {
    	String mensaje = "";
		Integer total = 0;
    	Usuario usuario = usuarioService.findById(numct);
    	if(usuario == null) {
    		mensaje = "Usuario no registrado.";
    		return null;
    	}
    	if(usuario.getPumaPuntos() == 0) {
    		mensaje = "Ya has alcanzado el máximo número de PumaPuntos.";
        	return null;
    	}
    	if(admin.getPuntos() > usuario.getPumaPuntos()){
    		mensaje = "No tienes suficientes PumaPuntos.";
    		return null;
    	}
    	total = usuario.getPumaPuntos() - admin.getPuntos(); //Suponiendo que los puntos que se pasen sea un parámetro válido.
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	return usuario;
    }
    
    
    @PutMapping("/usuarios/suma/version1/{numct}")
    public ResponseEntity<?> sumaPuntos(@PathVariable String numct, @RequestBody AdministrarPuntos pumaPuntos, BindingResult bindingResult){
		String mensaje = "";
		Integer total = 0;
    	if(bindingResult.hasErrors()){ //Primera validación
			mensaje = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
    	Usuario usuario = usuarioService.findById(numct);
    	if(usuario == null) {
    		mensaje = "Usuario no registrado.";
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	if(usuario.getPumaPuntos() == 500) {
    		mensaje = "Ya has alcanzado el máximo número de PumaPuntos.";
        	return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    	}
    	total = usuario.getPumaPuntos() + pumaPuntos.getPuntos(); //Suponiendo que los puntos que se pasen sea un parámetro válido.
    	if(total > 500) { //Se mochan los puntos extras.
    		mensaje = "¡Felicidades!\nAcumulaste " + (500 - usuario.getPumaPuntos())
    				+ " PumaPuntos con éxito.\nYa has alcanzado el número "
    				+ "máximo de PumaPuntos.";
    		total = 500;
    		usuario.setPumaPuntos(total);
        	usuarioService.save(usuario);
    		return new ResponseEntity<>(mensaje, HttpStatus.OK);
    	}
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	mensaje = "¡Felicidades!\nAcumulaste " + pumaPuntos.getPuntos()
				+ " PumaPuntos con éxito.";
    	return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/usuarios/resta/version1/{numct}")
    public ResponseEntity<String> restaPuntos(@PathVariable String numct, @RequestBody AdministrarPuntos pumaPuntos, BindingResult bindingResult){
		String mensaje = "";
		Integer total = 0;
    	if(bindingResult.hasErrors()){ //Primera validación
			mensaje = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
    	Usuario usuario = usuarioService.findById(numct);
    	if(usuario == null) {
    		mensaje = "Usuario no registrado.";
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	if(usuario.getPumaPuntos() == 0) {
    		mensaje = "Tienes 0 PumaPuntos."
    				+ "\nConsulta las actividades que puedes ralizar para obtener PumaPuntos.";
        	return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    	}
    	if(pumaPuntos.getPuntos() > usuario.getPumaPuntos()){
    		mensaje = "No tienes suficientes PumaPuntos.";
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	total = usuario.getPumaPuntos() - pumaPuntos.getPuntos(); //Suponiendo que los puntos que se pasen sea un parámetro válido.
    	usuario.setPumaPuntos(total);
    	usuarioService.save(usuario);
    	mensaje = "Se restaron  " + pumaPuntos.getPuntos() + " PumaPuntos.";
    	return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

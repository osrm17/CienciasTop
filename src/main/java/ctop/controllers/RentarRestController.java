package ctop.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import ctop.model.entity.Existencia;
import ctop.model.entity.Producto;
import ctop.model.entity.Rentar;
import ctop.model.entity.Usuario;
import ctop.model.service.ServiceInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a las rentas.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class RentarRestController {

    @Autowired
    private ServiceInterface<Rentar, Long> rentarService;

    @Autowired
    private ServiceInterface<Existencia, Long> existenciaService;

    @Autowired
    private ServiceInterface<Producto, String> productoService;

    @Autowired
    private ServiceInterface<Usuario, String> usuarioService;

    @GetMapping("/rentas")
    public Iterable<Rentar> findAll() {
        return rentarService.findAll();
    }

    @GetMapping("/rentas/{id}")
    public Rentar show(@PathVariable Long id) {
        return rentarService.findById(id);
    }

    @PostMapping("/rentas")
    @ResponseStatus(HttpStatus.CREATED)
    public Rentar create(@RequestBody Rentar renta) {
        return rentarService.save(renta);
    }

    @PutMapping("/rentas/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rentar update(@RequestBody Rentar nuevo, @PathVariable Long id) {
        Rentar actual = rentarService.findById(id);
        actual.setId(nuevo.getId());
        actual.setNumct(nuevo.getNumct());
        actual.setIdExistencia(nuevo.getIdExistencia());
        actual.setFechaDevolucion(nuevo.getFechaDevolucion());
        actual.setFechaRenta(nuevo.getFechaRenta());
        rentarService.save(actual);
        return actual;
    }

    @DeleteMapping("/rentas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rentarService.delete(id);
    }

    @GetMapping("/rentas/usuario/{numct}")
    public ResponseEntity<?> findAllPerUser(@PathVariable String numct) {
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
        Iterable<Rentar> iterable = rentarService.findAll();
        LinkedList<RentarDetalles> listWithUsrHist = new LinkedList<>();
        for (Rentar renta : iterable)
            if (renta.getNumct().equals(numct)) {
                Existencia existencia = existenciaService.findById(renta.getIdExistencia());
                Producto producto = productoService.findById(existencia.getCodigo());
                RentarDetalles rentaConDetalles = new RentarDetalles(producto.getCodigo(), producto.getNombre());
                rentaConDetalles.setId(renta.getId());
                rentaConDetalles.setNumct(renta.getNumct());
                rentaConDetalles.setIdExistencia(renta.getIdExistencia());
                rentaConDetalles.setFechaDevolucion(renta.getFechaDevolucion());
                rentaConDetalles.setFechaRenta(renta.getFechaRenta());
                listWithUsrHist.add(rentaConDetalles);
            }
        return new ResponseEntity<Iterable<RentarDetalles>>(listWithUsrHist, HttpStatus.OK);
    }

    @Getter
    @Setter
    private class RentarDetalles extends Rentar{
        public String codigoProducto;
        public String nombreProducto;

        public RentarDetalles(String codigoProducto, String nombreProducto) {
            super();
            this.codigoProducto = codigoProducto;
            this.nombreProducto = nombreProducto;
        }
    }
}

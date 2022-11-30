package ctop.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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

import ctop.model.entity.Producto;
import ctop.model.service.ServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los productos.
 * 
 * @version 1.1
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private ServiceInterface<Producto, String> productoService;

    @Secured({ "ROLE_ADMIN", "ROLE_USER", "ROLE_PROVEEDOR" })
    @GetMapping("/productos")
    public Iterable<Producto> index() {
        return productoService.findAll();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER", "ROLE_PROVEEDOR" })
    @GetMapping("/productos/{codigo}")
    public Producto show(@PathVariable String codigo) {
        return productoService.findById(codigo);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_PROVEEDOR" })
    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        Producto productoNuevo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            if (null != productoService.findById(producto.getCodigo())) {
                response.put("mensaje", "Error al agregar el producto en la base de datos.");
                response.put("error", "Llave duplicada en los productos.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            productoNuevo = productoService.save(producto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al agregar el producto en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El producto ha sido agregado con éxito!");
        response.put("producto", productoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_PROVEEDOR" })
    @PutMapping("/productos/{codigo}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto update(@RequestBody Producto nuevo, @PathVariable String codigo) {
        Producto actual = productoService.findById(codigo);
        actual.setCodigo(nuevo.getCodigo());
        actual.setCostoPuntos(nuevo.getCostoPuntos());
        actual.setDescripcion(nuevo.getDescripcion());
        actual.setDiasRenta(nuevo.getDiasRenta());
        actual.setNombre(nuevo.getNombre());
        actual.setNumct(nuevo.getNumct());
        productoService.save(actual);
        return actual;
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/productos/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        productoService.delete(codigo);
    }
}

package ctop.controllers;

import java.util.HashMap;
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

import ctop.model.entity.Producto;
import ctop.model.service.ServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los productos.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private ServiceInterface<Producto, String> productoService;

    @GetMapping("/productos")
    public Iterable<Producto> index() {
        return productoService.findAll();
    }

    @GetMapping("/productos/{codigo}")
    public ResponseEntity<?> show(@PathVariable String codigo) {
        Producto producto = null;
        Map<String, Object> response = new HashMap<>();
        // Error con el servidor o base de datos.
        try {
            producto = productoService.findById(codigo);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta del producto en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Error con el id ingresado.
        if(producto == null) {
            response.put("mensaje", "¡El producto ".concat(codigo.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

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

    @PutMapping("/productos/{codigo}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Producto nuevo, @PathVariable String codigo) {
        Producto actual = this.productoService.findById(codigo);
        Producto productoUpdate = null;
        Map<String,Object> response = new HashMap<>();
        //Error con el id ingresado.
        if(actual == null) {
            response.put("mensaje", "Error: no se puede editar el producto ".concat(codigo.toString().concat(" porque no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setCodigo(nuevo.getCodigo());
            actual.setNumct(nuevo.getNumct());
            actual.setNombre(nuevo.getNombre());
            actual.setCostoPuntos(nuevo.getCostoPuntos());
            actual.setDiasRenta(nuevo.getDiasRenta());
            actual.setDescripcion(nuevo.getDescripcion());
            productoUpdate = this.productoService.save(actual);
        }catch(DataAccessException e) {
            response.put("mensaje", "Error al actualizar el producto en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "¡El producto ha sido actualizado con éxito!");
        response.put("producto", productoUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        productoService.delete(codigo);
    }
}
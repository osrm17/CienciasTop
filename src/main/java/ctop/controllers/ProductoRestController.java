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

import ctop.model.entity.Producto;
import ctop.model.service.ServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los productos.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = {"http://localhost:4200"})
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
    public Producto show(@PathVariable String codigo) {
        return productoService.findById(codigo);
    }

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

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

    @DeleteMapping("/productos/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        productoService.delete(codigo);
    }
}

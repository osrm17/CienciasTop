package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Producto;
import ctop.model.service.ProductoServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los productos.
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private ProductoServiceInterface productoService;

    @GetMapping("/productos")
    public Iterable<Producto> index() {
        return productoService.findAll();
    }

}

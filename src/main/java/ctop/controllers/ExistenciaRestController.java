package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Existencia;
import ctop.model.service.ExistenciaServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a las existencias.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ExistenciaRestController {

    @Autowired
    private ExistenciaServiceInterface existenciaService;

    @GetMapping("existencias")
    public Iterable<Existencia> findAll() {
        return existenciaService.findAll();
    }
}

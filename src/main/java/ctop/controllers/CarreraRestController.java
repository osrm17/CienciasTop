package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Carrera;
import ctop.model.service.CarreraServiceInterface;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a las carreras.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CarreraRestController {

    @Autowired
    private CarreraServiceInterface carreraService;

    @GetMapping("carreras")
    public Iterable<Carrera> findAll() {
        return carreraService.findAll();
    }
}

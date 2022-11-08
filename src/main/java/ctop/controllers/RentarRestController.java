package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Rentar;
import ctop.model.service.RentarServiceInterface;

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
    private RentarServiceInterface rentarService;

    @GetMapping("/rentas")
    public Iterable<Rentar> findAll() {
        return rentarService.findAll();
    }
}

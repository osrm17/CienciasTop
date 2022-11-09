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

import ctop.model.entity.Carrera;
import ctop.model.entity.CarreraId;
import ctop.model.service.ServiceInterface;

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
    private ServiceInterface<Carrera, CarreraId> carreraService;

    @GetMapping("/carreras")
    public Iterable<Carrera> findAll() {
        return carreraService.findAll();
    }

    @GetMapping("/carreras/{numct}-{carrera}")
    public Carrera show(@PathVariable String numct, @PathVariable String carrera) {
        return carreraService.findById(new CarreraId(numct, carrera));
    }

    @PostMapping("/carreras")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrera create(@RequestBody Carrera carrera) {
        return carreraService.save(carrera);
    }

    // Al intentar utilizar este metodo manda error, mejor borrar y luego crear de nuevo.
    @PutMapping("/carreras/{numct}-{carrera}")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrera update(@RequestBody Carrera nuevo, @PathVariable String numct, @PathVariable String carrera) {
        Carrera actual = carreraService.findById(new CarreraId(numct, carrera));
        actual.setCarreraId(nuevo.getCarreraId());
        carreraService.save(actual);
        return actual;
    }

    @DeleteMapping("/carreras/{numct}-{carrera}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String numct, @PathVariable String carrera) {
        carreraService.delete(new CarreraId(numct, carrera));
    }
}

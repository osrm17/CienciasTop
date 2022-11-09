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

import ctop.model.entity.Existencia;
import ctop.model.service.ServiceInterface;

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
    private ServiceInterface<Existencia, Long> existenciaService;

    @GetMapping("/existencias")
    public Iterable<Existencia> findAll() {
        return existenciaService.findAll();
    }

    @GetMapping("/existencias/{id}")
    public Existencia show(@PathVariable Long id) {
        return existenciaService.findById(id);
    }

    @PostMapping("/existencias")
    @ResponseStatus(HttpStatus.CREATED)
    public Existencia create(@RequestBody Existencia existencia) {
        return existenciaService.save(existencia);
    }

    @PutMapping("/existencias/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Existencia update(@RequestBody Existencia nuevo, @PathVariable Long id) {
        Existencia actual = existenciaService.findById(id);
        actual.setId(nuevo.getId());
        actual.setCodigo(nuevo.getCodigo());
        actual.setEstaRentado(nuevo.isEstaRentado());
        existenciaService.save(actual);
        return actual;
    }

    @DeleteMapping("/existencias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        existenciaService.delete(id);
    }
}

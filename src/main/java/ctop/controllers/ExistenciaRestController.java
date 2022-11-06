package ctop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Existencia;
import ctop.model.service.ExistenciaServiceInterface;

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

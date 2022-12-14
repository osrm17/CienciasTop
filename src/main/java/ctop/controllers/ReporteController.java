package ctop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctop.model.entity.Producto;
import ctop.model.entity.Usuario;
import ctop.model.service.ReporteService;

/**
 * Clase controlador que se encarga de manejar las peticiones con
 * respecto a los reportes.
 * 
 * @version 1.0
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reportes/activos")
    public  List<String> getActivos() {
       return reporteService.getActivos();
    }

    @GetMapping("/reportes/inactivos")
    public int getInactivos() {
       return reporteService.getInactivos();
    }

    @GetMapping("/reportes/baratos")
    public List<Producto> getBaratos() {
       return reporteService.getBaratos();
    }

    @GetMapping("/reportes/masrentados")
    public List<Producto> getMasRentados() {
       return reporteService.getMasRentados();
    }

    @GetMapping("/reportes/devtardias")
    public List<Usuario> devTardias() {
       return reporteService.devTardias();
    }

    @GetMapping("/reportes/masrentas")
    public List<Usuario> masRentas() {
       return reporteService.masRentas();
    }

    
}

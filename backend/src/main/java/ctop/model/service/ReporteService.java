package ctop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ctop.model.dao.ProductoDAO;
import ctop.model.dao.UsuarioDAO;
import ctop.model.entity.Producto;
import ctop.model.entity.Usuario;

/**
 * Clase que tiene la implementacion del servicio que manejara los reportes.
 * 
 * @version 1.0
 */
@Service
public class ReporteService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private ProductoDAO productoDAO;

    public List<String> getActivos() {
        return (List<String>) usuarioDAO.getActivos();
    }

    public int getInactivos() {
        return usuarioDAO.getInactivos();
    }

    public List<Producto> getBaratos() {
        return (List<Producto>) productoDAO.getBaratos();
    }

    public List<Producto> getMasRentados() {
        return (List<Producto>) productoDAO.getMasRentados();
    }

    public List<Usuario> masRentas() {
        return (List<Usuario>) usuarioDAO.masRentas();
    }

    public List<Usuario> devTardias() {
        return (List<Usuario>) usuarioDAO.devTardias();
    }
    
}

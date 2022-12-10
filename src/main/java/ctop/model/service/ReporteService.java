package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.ProductoDAO;
import ctop.model.dao.UsuarioDAO;

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

    public int getInactivos() {
        return usuarioDAO.getInactivos();
    }
    
}

package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.ProductoDAO;
import ctop.model.entity.Producto;

/**
 * Clase que tiene la implementacion del servicio que manejara los productos.
 * 
 * @version 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoServiceInterface {

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Producto> findAll() {
        return productoDAO.findAll();
    }
}

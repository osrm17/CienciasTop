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
    ProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Producto> findAll() {
        return productoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(String codigo) {
        return productoDAO.findById(codigo).orElse(null);
    }

    @Override
    @Transactional()
    public Producto save(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    @Transactional()
    public void delete(String codigo) {
        productoDAO.deleteById(codigo);        
    }



}

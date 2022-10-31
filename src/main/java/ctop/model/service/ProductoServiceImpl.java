package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.ProductoDAO;
import ctop.model.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoServiceInterface {
   
    @Autowired
    ProductoDAO productoDAO;
    
    @Transactional(readOnly = true)
    public Iterable<Producto> findAll() {
        return productoDAO.findAll();
    }
}

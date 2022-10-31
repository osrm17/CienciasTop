package ctop.model.service;

import ctop.model.entity.Producto;

public interface ProductoServiceInterface {
    
    public Iterable<Producto> findAll();
}

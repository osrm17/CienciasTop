package ctop.model.service;

import ctop.model.entity.Producto;

/**
 * Interfaz que define implementacion que debe tener
 * un servicio que maneja los productos.
 * 
 * @version 1.0
 */
public interface ProductoServiceInterface {

    /**
     * Metodo que devuelve todos los productos disponibles en la
     * base de datos.
     * 
     * @return Iterable-Producto -- devuelve un iterable de productos.
     */
    public Iterable<Producto> findAll();
}

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

    /**
     * Dado el codigo de un producto devuelve el producto.
     * @param codigo que con el que se identifica al producto
     * @return producto al cual esta asociado el codigo en la base de datos
     */
    public Producto findById(String codigo);

    /**
     * @param producto
     * @return
     */
    public Producto save(Producto producto);


    /**
     * @param codigo
     */
    public void delete(String codigo);

}

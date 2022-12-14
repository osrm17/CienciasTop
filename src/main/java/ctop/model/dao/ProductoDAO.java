package ctop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Producto;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a los productos que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface ProductoDAO extends CrudRepository<Producto, String> {

    @Query(value = "SELECT * FROM ctop.producto ORDER BY costopuntos ASC LIMIT 3", 
           nativeQuery = true)
    public List<Producto> getBaratos();


    @Query(value = "SELECT * FROM ctop.mas_rentados_del_mes()", 
           nativeQuery = true)
    public List<Producto> getMasRentados();


}

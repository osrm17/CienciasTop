package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Rentar;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a los productos que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface RentarDAO extends CrudRepository<Rentar, Long> {

}

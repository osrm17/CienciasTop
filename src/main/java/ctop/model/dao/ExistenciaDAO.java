package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Existencia;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a las existencias que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface ExistenciaDAO extends CrudRepository<Existencia, Long> {

}

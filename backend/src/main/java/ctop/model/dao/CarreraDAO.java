package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Carrera;
import ctop.model.entity.CarreraId;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a las carreras de los usuarios que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface CarreraDAO extends CrudRepository<Carrera, CarreraId> {

}

package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Usuario;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a los usuarios que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface UsuarioDAO extends CrudRepository<Usuario, String> {

}

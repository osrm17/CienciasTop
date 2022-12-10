package ctop.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import ctop.model.entity.Usuario;

/**
 * Interfaz que define el comportamiento esencial para acceder
 * a los usuarios que se encuentran en la base de datos.
 * 
 * @version 1.0
 */
public interface UsuarioDAO extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT COUNT(*) FROM ctop.usuario WHERE estaactivo IS FALSE", 
           nativeQuery = true)
    public int getInactivos();
}
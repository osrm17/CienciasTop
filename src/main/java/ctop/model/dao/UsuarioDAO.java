package ctop.model.dao;

import java.util.List;

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

    @Query(value = "SELECT * FROM ctop.activos_por_carrera()", 
           nativeQuery = true)
    public List<String> getActivos();

    @Query(value = "SELECT COUNT(*) FROM ctop.usuario WHERE estaactivo IS FALSE", 
           nativeQuery = true)
    public int getInactivos();

    @Query(value = "SELECT * FROM ctop.usuarios_con_mas_rentas()", 
           nativeQuery = true)
    public List<Usuario> masRentas();

    @Query(value = "SELECT * FROM ctop.usuarios_dev_tardias()", 
           nativeQuery = true)
    public List<Usuario> devTardias();

}
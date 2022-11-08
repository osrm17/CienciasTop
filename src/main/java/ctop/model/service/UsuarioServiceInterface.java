package ctop.model.service;

import ctop.model.entity.Usuario;

/**
 * Interfaz que define la implementacion que debe tener
 * un servicio que maneja los usuarios.
 * 
 * @version 1.0
 */
public interface UsuarioServiceInterface {

    /**
     * Metodo que devuelve todos los usuarios disponibles en la
     * base de datos.
     * 
     * @return Iterable-Usuario -- devuelve un iterable de usuarios.
     */
    public Iterable<Usuario> findAll();
}

package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, String>{
    
}

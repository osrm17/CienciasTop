package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.UsuarioDAO;
import ctop.model.entity.Usuario;

/**
 * Clase que tiene la implementacion del servicio que manejara los usuarios.
 * 
 * @version 1.0
 */
@Service
public class UsuarioServiceImpl implements ServiceInterface<Usuario, String> {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(String numct) {
        return usuarioDAO.findById(numct).orElse(null);
    }

    @Override
    @Transactional()
    public Usuario save(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    @Override
    @Transactional()
    public void delete(String numct) {
        usuarioDAO.deleteById(numct);
    }
}

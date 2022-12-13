package ctop.model.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UsuarioServiceImpl implements ServiceInterface<Usuario, String>, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Usuario usuario = usuarioDAO.findById(username).orElse(null);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(3);

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(usuario.isEsAdministrador()){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if(usuario.isEsProveedor()){
            authorities.add(new SimpleGrantedAuthority("ROLE_PROVEEDOR"));
        }
		
		return new User(usuario.getNumct(), usuario.getContrasenia(), usuario.isEstaActivo(), true, true, true, authorities);
	}

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

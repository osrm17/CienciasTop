package ctop.model.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import ctop.model.entity.Usuario;
import ctop.model.service.ServiceInterface;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private ServiceInterface<Usuario, String> usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findById(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Bienvenido: ".concat(authentication.getName()));
		info.put("numct", usuario.getNumct());
		info.put("nombre", usuario.getNombre());
		info.put("paterno", usuario.getPaterno());
		info.put("materno", usuario.getMaterno());
		info.put("correo", usuario.getCorreo());
		info.put("celular", usuario.getCelular());
		info.put("pumapuntos", usuario.getPumaPuntos());
		info.put("esProveedor", usuario.isEsProveedor());
		info.put("esAdministrador", usuario.isEsAdministrador());
		info.put("estaActivo", usuario.isEstaActivo());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}


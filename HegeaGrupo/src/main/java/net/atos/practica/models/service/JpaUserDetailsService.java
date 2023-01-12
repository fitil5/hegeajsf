package net.atos.practica.models.service;

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

import net.atos.practica.controller.loginController;
import net.atos.practica.entity.UsuarioEntity;
import net.atos.practica.repository.IUsuarioRepository;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private loginController login;

	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByUsername(username);

		if (usuario == null) {
			logger.error("Error login: no existe el usuario '" + username + "' no existe");
			login.setMensajeRe("El usuario no existe");
			throw new UsernameNotFoundException("Username: " + username + "no existe en el sistema");
		
	}else if (usuario.getEnabled()==false) {
		login.setMensajeRe("El usuario no tiene acceso a la pagina");
	}else {
		login.setMensajeRe("La contraseña no es correcta");
	}
		logger.info("Role: ".concat(usuario.getRoles().getAuthority()));
		List<GrantedAuthority> authorities = new ArrayList<>() ;
				authorities.add(new SimpleGrantedAuthority(usuario.getRoles().getAuthority()));
		/*for (net.atos.practica.entity.RoleEntity role : usuario.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}*/

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,authorities);

	}
}

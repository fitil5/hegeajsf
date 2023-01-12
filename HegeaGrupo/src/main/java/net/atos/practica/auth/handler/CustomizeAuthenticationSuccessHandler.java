package net.atos.practica.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.atos.practica.entity.UsuarioEntity;
import net.atos.practica.repository.IUsuarioRepository;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UsuarioEntity usuario;
	public boolean adminchange;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// set our response to OK status
		usuario = usuarioRepository.findByUsername(authentication.getName());
		response.setStatus(HttpServletResponse.SC_OK);

		boolean admin = false;

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30000);

		logger.info("AT onAuthenticationSuccess(...) function!");

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ("ROLE_ADMIN".equals(auth.getAuthority())) {
				admin = true;
			}

		}

		if (usuario.getEnabled()) {

			if (!usuario.getChangepassword()) {
				// FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/user/changePasword.xhtml");
				adminchange = admin; 
				response.sendRedirect("/changePasword.xhtml");
			} else {
				if (admin) {
					response.sendRedirect("/pages/admin/homeadmin.xhtml");
					System.out.println(authentication.getName());
				} else {
					response.sendRedirect("/pages/user/homeuser.xhtml");
				}
			}
		}

	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
}
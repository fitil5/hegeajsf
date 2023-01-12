package net.atos.practica;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;

import net.atos.practica.auth.handler.CustomizeAuthenticationSuccessHandler;
import net.atos.practica.models.service.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomizeAuthenticationSuccessHandler customize;

	// @Autowired
	// private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

		/*
		 * build.jdbcAuthentication().dataSource(dataSource).passwordEncoder(
		 * passwordEncoder)
		 * .usersByUsernameQuery("select username, password, enabled from users where username=?"
		 * )
		 * .authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on(a.user_id=u.id) where u.username=?"
		 * );
		 */
		/*
		 * PasswordEncoder encoder =
		 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); UserBuilder users
		 * = User.builder().passwordEncoder(encoder::encode);
		 * build.inMemoryAuthentication()
		 * .withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
		 * .withUser(users.username("pepote").password("12345").roles("USER"));
		 */

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/",  "/frmLogin.xhtml", "/changePassword.xhtml")
				.permitAll().antMatchers("/pages/user/**").hasAnyRole("USER","ADMIN").antMatchers("/pages/admin/**")
				.hasRole("ADMIN")
				.antMatchers("/images/**").permitAll() 
				.antMatchers("/pages/user/Alta_actividades.xhtml").hasAnyRole("USER","ADMIN")
				// Para autentificar las paginas.
				.anyRequest().authenticated().and().formLogin().loginPage("/frmLogin.xhtml").successHandler(customize)
				.failureUrl("/frmLogin.xhtml?error=true").permitAll() // PARA MANDAR AL LOGIN NUESTRO
				// .formLogin().successHandler(customize).permitAll()
				.and().logout();
		// http.logout().logoutSuccesUrl("/login.xhtml");
		http.logout().logoutSuccessUrl("/frmLogin.html");
		http.csrf().disable();

	}
}

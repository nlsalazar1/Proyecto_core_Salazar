package com.springboot.clienteapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder PassEncoder;	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  // Le damos los permisos de acuerdo al rol (user o admin)
		
		http.authorizeRequests()
		.antMatchers("/","/home","/index","/css/**","/js/**","/images/**").permitAll()
		.antMatchers("/views/clientes/").hasAnyRole("USER")  //le damos permiso de ver solo la lista de clientes
		.antMatchers("/views/clientes/create").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/save").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/edit/**").hasAnyRole("ADMIN")   //Revisamos en Cliente Controller que necesitan los metodo
		.antMatchers("/views/clientes/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/").hasAnyRole("USER")  //le damos permiso de ver solo la lista de clientes
		.antMatchers("/views/inmuebles/create").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/save").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/edit/**").hasAnyRole("ADMIN")   //Revisamos en Cliente Controller que necesitan los metodo
		.antMatchers("/views/inmuebles/delete/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
	}


	@Autowired
	public void configureSecurityGlobal(AuthenticationManagerBuilder buider) {
		try {
			buider.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(PassEncoder)
			.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?") //Realizamos la verificacion de que exista el usuario
			.authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id = u.id WHERE u.username=?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//Verificamos el rol del Usuario
	}
	
}

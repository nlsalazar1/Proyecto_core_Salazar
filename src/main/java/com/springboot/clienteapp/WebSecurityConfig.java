package com.springboot.clienteapp;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.clienteapp.util.LoginSuccessMessage;

//public class WebSecurityConfig{

@EnableGlobalMethodSecurity(securedEnabled=true) // video 12 seguridad - para dar los permisos USER O ADMIN
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder PassEncoder;	
	
	@Autowired
	private LoginSuccessMessage successMessage;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  // Le damos los permisos de acuerdo al rol (user o admin)
		
		http.authorizeRequests()
		.antMatchers("/","/home","/index","/css/**","/js/**","/images/**","//").permitAll()
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .regexMatchers("^./.*$").permitAll() // Permitir URLs que contengan "//"       
		.antMatchers("/views/clientes/").hasAnyRole("USER")  //le damos permiso de ver solo la lista de clientes
		.antMatchers("/views/clientes/").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/create").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/save").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/edit/**").hasAnyRole("ADMIN")   //Revisamos en Cliente Controller que necesitan los metodo
		.antMatchers("/views/clientes/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/").hasAnyRole("USER")  //le damos permiso de ver solo la lista de clientes
		.antMatchers("/views/inmuebles/").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/create").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/save").hasAnyRole("ADMIN")
		.antMatchers("/views/inmuebles/edit/**").hasAnyRole("ADMIN")   //Revisamos en Cliente Controller que necesitan los metodo
		.antMatchers("/views/inmuebles/delete/**").hasAnyRole("ADMIN")
		.antMatchers("/views/clientes/inmueblesCli/").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.successHandler(successMessage)
			.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
	}
//}

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
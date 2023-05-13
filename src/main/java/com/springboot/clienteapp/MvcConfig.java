package com.springboot.clienteapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Bean //lo ponemos para poder inyectarlo en cualquier otra clase que se necesite este metodo
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
}

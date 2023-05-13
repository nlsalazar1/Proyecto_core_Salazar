package com.springboot.clienteapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootClienteappApplication {  // para encriptar usamos  "implements CommandLineRunner"

	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootClienteappApplication.class, args);
	}
	
	
	
	
	
	//Usamos para probar la encriptacion
		/*@Autowired
		private BCryptPasswordEncoder passEncoder;*/		
	

	//Metodo para crear una encriptacion de una contraseña que queramos
	/*@Override
	public void run(String... args) throws Exception {
		String pass1 = "user";
		String pass2 = "admin";
		
		System.out.println(passEncoder.encode(pass1));
		System.out.println(passEncoder.encode(pass2));		
		
	}*/

}

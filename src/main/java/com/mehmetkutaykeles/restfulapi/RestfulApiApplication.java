package com.mehmetkutaykeles.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mehmetkutaykeles.restfulapi")
public class RestfulApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestfulApiApplication.class, args);
	}

}

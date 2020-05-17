package com.kt.springmvc.gestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class GestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorApplication.class, args);
		System.out.println(test);
	}

}

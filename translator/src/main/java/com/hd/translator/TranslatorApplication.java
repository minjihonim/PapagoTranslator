package com.hd.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TranslatorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}

}

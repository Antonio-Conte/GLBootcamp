package ar.com.gl.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import ar.com.gl.customer.config.SwaggerConfig;
import ar.com.gl.customer.controller.CustomerController;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.services.CustomerService;

@SpringBootApplication
@EnableJpaRepositories
@Import(SwaggerConfig.class)
public class Main{



	public static void main(final String[] args ){
		SpringApplication.run(Main.class);
	}
	

	
}
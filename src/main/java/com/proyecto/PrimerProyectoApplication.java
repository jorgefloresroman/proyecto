//Aplicación principal

package com.proyecto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proyecto.conf.SwaggerConfiguration;
import com.proyecto.dao.ContactRepository;
import com.proyecto.dto.Contact;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class PrimerProyectoApplication implements WebMvcConfigurer, CommandLineRunner{
	
	@Autowired
	ContactRepository contactRepository;


	public static void main(String[] args) {
		SpringApplication.run(PrimerProyectoApplication.class, args);
	}
	
	   @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 
	           registry.addResourceHandler("swagger-ui.html")
	                    .addResourceLocations("classpath:/META-INF/resources/");
	 
	    }

	@Override
	public void run(String... args) throws Exception {
		
		Contact contact = new Contact();
		contact.setFirstName("primerNombre");
		contact.setLastName("segundoNombre");
		contact.setEmail("emilio@mail.tool");
		contact.setPhoneNumber("+3465756575657");
		contactRepository.save(contact);
		
	}
	   
	   

}

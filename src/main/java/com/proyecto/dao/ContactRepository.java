//Capa de acceso a datos

package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long>{
	
	public abstract Contact findByFirstNameAndId(String firstName, long id);

}

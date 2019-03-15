package com.proyecto.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.ContactRepository;
import com.proyecto.dto.Contact;

@Service
public class ContactService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);
	
	@Autowired
	ContactRepository dao;
	
	public Contact save(Contact contact) {
		LOGGER.info("Se procede a guardar el contacto con el nombre {} y el email {}",contact.getFirstName(),contact.getEmail());
		return dao.saveAndFlush(contact);
	}
	
	
	public List<Contact> getContacts(){
		return dao.findAll();
	}
	
	public boolean deleteContact(String firstName, Long id) {
		Contact contact = dao.findByFirstNameAndId(firstName, id);		
		
		try{
			dao.delete(contact);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	
}

//Capa de presentación

package com.proyecto.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dto.Contact;
import com.proyecto.service.ContactService;

@RestController
@RequestMapping(value="/v1")
public class ContactsApi {
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ContactService contactService;
	 
	
	 @RequestMapping(value="/newcontact", method=RequestMethod.POST)	
	 public ContactResponse updateOrSave(@RequestBody @Valid ContactRequest contactRequest){
		 //Mapeamos ContactRequest a la clase Contact (Al DTO)	
		 Contact contact = mapper.map(contactRequest, Contact.class);
		 //Llamamos a la logica de negocio
		 	Contact updatedContact = contactService.save(contact);
		 	//Volvemos a mapear el DTO de respuesta a la clase ContactResponse
		 	ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class);
		 	return contactResponse;
		 	
	    }
	 
	 @RequestMapping(value="/getcontacts", method=RequestMethod.GET)
	 public List<ContactResponse> getContacts(){
		 List<Contact> list = contactService.getContacts();
		 
				 List<ContactResponse> temp = new ArrayList<ContactResponse>(list.size());
				 for (Contact listVar : list) {
					 ContactResponse response= (ContactResponse) mapper.map(listVar, ContactResponse.class);
				     temp.add(response);
				 }
		 
		 return temp;	 
	 }
	 
	 
		@RequestMapping(value="/deletecontact/{id}/{firstName}",  method=RequestMethod.DELETE)
		public ResponseEntity borrarNota(@PathVariable("id") long id, @PathVariable("firstName") String firstName) { 
				boolean x=contactService.deleteContact(firstName, id);
					if(x) {
						return ResponseEntity.status(HttpStatus.OK).body(new DeleteMask("020","El id: " + id + " ha sido borrado correctamente."));
						
					}else
					{
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DeleteMask("000","No ha sido posible borrar el elemento seleccionado."));
					}
		}

}

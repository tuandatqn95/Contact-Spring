package travis.spring.service;

import java.util.List;
import java.util.Optional;

import travis.spring.model.Contact;

public interface ContactService {

	List<Contact> getAllContact();

	boolean addContact(Contact contact);

	Optional<Contact> getContactById(int id);

	void deleteContactById(int id);

	List<Contact> searchContact(String q);

	List<Contact> searchContact(String name, String email, String phone);

}
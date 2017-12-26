package travis.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travis.spring.model.Contact;
import travis.spring.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> getAllContact() {
		return contactRepository.findAll();
	}

	public boolean addContact(Contact contact) {
		return (contactRepository.save(contact) != null);

	}

	public Optional<Contact> getContactById(int id) {
		return contactRepository.findById(id);
	}

	public void deleteContactById(int id) {
		contactRepository.deleteById(id);
	}

	public List<Contact> searchContact(String q) {
		return contactRepository.findByNameContaining(q);
	}

	public List<Contact> searchContact(String name, String email, String phone) {
		return contactRepository.findByNameContainingOrEmailContainingOrPhoneContaining(name, email, phone);
	}

}

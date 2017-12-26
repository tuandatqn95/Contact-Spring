package travis.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import travis.spring.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByNameContaining(String q);

	List<Contact> findByNameContainingOrEmailContainingOrPhoneContaining(String name, String email, String phone);
}

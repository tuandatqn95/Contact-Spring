package travis.spring.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import travis.spring.model.Contact;
import travis.spring.service.ContactServiceImpl;

@Controller
public class ContactController {

	@Autowired
	private ContactServiceImpl contactService;

	@GetMapping(value = { "", "/contact" })
	public String contact(Model model, HttpServletResponse response) {
		model.addAttribute("contacts", contactService.getAllContact());
		return "list";
	}

	@GetMapping("/add")
	public String getAddContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "form";
	}

	@PostMapping(value = "/add")
	public String postAddContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "form";
		}
		contactService.addContact(contact);
		redirect.addFlashAttribute("success", "Saved contact successfully!");
		return "redirect:/contact";
	}

	@GetMapping("/contact/{id}/edit")
	public String getEditContact(@PathVariable int id, Model model) {
		model.addAttribute("contact", contactService.getContactById(id));
		return "form";
	}

	@GetMapping("/contact/{id}/delete")
	public String getConfirmDeleteContact(@PathVariable int id, Model model) {
		contactService.deleteContactById(id);
		return "redirect:/contact";
	}

	@PostMapping("/contact/search")
	public String postSearchContact(@RequestParam("q") String q, Model model) {
		if (q.equals(""))
			return "redirect:/contact";
		model.addAttribute("contacts", contactService.searchContact(q, q, q));
		return "list";
	}
}

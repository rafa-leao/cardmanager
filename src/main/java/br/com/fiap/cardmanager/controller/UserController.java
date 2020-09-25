package br.com.fiap.cardmanager.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.cardmanager.model.User;
import br.com.fiap.cardmanager.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping()
	public ModelAndView users() {
		List<User> users = userRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("users");
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	@PostMapping()
	public String save(@Valid User user, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return "user_new";
		userRepository.save(user);
		attributes.addFlashAttribute("message", getMessage("message.newuser.success"));
		return "redirect:/user";
	}

	@RequestMapping("new")
	public String formUser(User user) {
		return "user_new";
	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
		return "redirect:/user";
	}
	
	@GetMapping("/{id}")
	public ModelAndView editUserForm(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("user_edit");
		modelAndView.addObject("user", user);
		return modelAndView;		
	}

	@PostMapping("/update")
	public String updateUser(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return "user_edit";
		userRepository.save(user);
		redirect.addFlashAttribute("message", getMessage("message.edituser.success"));
		return "redirect:/user"; 
	}
	
	private String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
}
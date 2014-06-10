package by.project.my.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.project.my.model.User;
import by.project.my.service.UserService;
import by.project.my.validator.SignupValidator;

@Controller
@RequestMapping("/signup")
public class SignupController {

	public static final String USER_ATTRIBUTE = "user";
	public static final String EXCEPTION_ATTRIBUTE = "exception";

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SignupValidator());
	}

	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public User register() {
		User user = new User();
		return user;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleRegistration(@Validated @ModelAttribute User user,
			BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		if (this.userService.addUser(user)) {
			session.setAttribute(USER_ATTRIBUTE, user.getUsername());
			return "redirect:/welcome/" + user.getUsername();
		} else {
			model.addAttribute(EXCEPTION_ATTRIBUTE,
					"such user is already exist");
			return "signup";
		}
	}
}

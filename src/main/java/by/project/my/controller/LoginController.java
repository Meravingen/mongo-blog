package by.project.my.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.project.my.model.User;
import by.project.my.service.AuthenticationException;
import by.project.my.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	public static final String USER_ATTRIBUTE = "user";

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public void login() {
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleLogin(@RequestParam String username,
			@RequestParam String password, HttpSession session)
			throws AuthenticationException {
		User user = this.userService.login(username, password);
		session.setAttribute(USER_ATTRIBUTE, user.getUsername());
		return "redirect:/welcome/" + user.getUsername();
	}

}

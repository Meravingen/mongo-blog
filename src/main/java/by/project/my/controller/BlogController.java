package by.project.my.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.project.my.model.Comment;
import by.project.my.model.Post;
import by.project.my.model.User;
import by.project.my.service.PostService;

@Controller
public class BlogController {

	private static final String USER_ATTRIBUTE = "user";
	private static final String POSTS_ATTRIBUTE = "myposts";
	private static final String POST_ATTRIBUTE = "post";

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String blog(Model model) {
		List<Post> posts = postService.findByDateDescending(10);
		model.addAttribute(POSTS_ATTRIBUTE, posts);
		return "blog";
	}

	@RequestMapping(value = "/welcome/{username}", method = RequestMethod.GET)
	public String welcome(@PathVariable String username, HttpSession session) {
		User user = (User) session.getAttribute(USER_ATTRIBUTE);
		if (user == null) {
			return "login";
		} else {
			return "welcome";
		}
	}

	@RequestMapping(value = "/post/{permalink}", method = RequestMethod.GET)
	public String postByPermalink(@PathVariable String permalink, Model model,
			@ModelAttribute Comment comment) {
		Post post = postService.findPostByPermalink(permalink);
		model.addAttribute(POST_ATTRIBUTE, post);
		if (post == null) {
			return "post_not_found";
		} else {
			model.addAttribute(POST_ATTRIBUTE, post);
			return "post";
		}
	}

	// @RequestMapping(value = "/post/{tag}", method = RequestMethod.GET)
	// public String postByTag(@PathVariable String tag, Model model) {
	// List<Post> posts = postService.findByTagDateDescending(tag);
	// model.addAttribute(POSTS_ATTRIBUTE, posts);
	// return "blog";
	// }

}

package by.project.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.project.my.model.Post;
import by.project.my.service.PostService;

@Controller
public class BlogController {

	private static final String USER_ATTRIBUTE = "user";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String EMAIL_ATTRIBUTE = "email";
	private static final String BODY_ATTRIBUTE = "body";
	private static final String POSTS_ATTRIBUTE = "myposts";
	private static final String POST_ATTRIBUTE = "post";
	private static final String COMMENT_ATTRIBUTE = "comment";

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
		String user = (String) session.getAttribute(USER_ATTRIBUTE);
		if (user == null) {
			return "login";
		} else {
			return "welcome";
		}
	}

	@RequestMapping(value = "/post/{permalink}", method = RequestMethod.GET)
	public String post(@PathVariable String permalink, Model model) {
		Post post = postService.findPostByPermalink(permalink);
		if (post == null) {
			return "post_not_found";
		} else {
			Map<String, String> newComment = new HashMap<String, String>();
			newComment.put(NAME_ATTRIBUTE, "");
			newComment.put(EMAIL_ATTRIBUTE, "");
			newComment.put(BODY_ATTRIBUTE, "");
			model.addAttribute(POST_ATTRIBUTE, post);
			model.addAttribute(COMMENT_ATTRIBUTE, newComment);
			return "entry";
		}
	}
}

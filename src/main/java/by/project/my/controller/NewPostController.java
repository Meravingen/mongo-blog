package by.project.my.controller;

import java.util.ArrayList;
import java.util.List;

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

import by.project.my.model.NewPost;
import by.project.my.model.User;
import by.project.my.service.PostService;
import by.project.my.validator.NewpostValidator;

@Controller
@RequestMapping("/newpost")
public class NewPostController {

	private static final String USER_ATTRIBUTE = "user";

	@Autowired
	private PostService postService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new NewpostValidator());
	}

	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public String newpost(HttpSession session, Model model) {
		User user = (User) session.getAttribute(USER_ATTRIBUTE);
		if (user == null) {
			return "login";
		} else {
			NewPost newPost = new NewPost();
			model.addAttribute(newPost);
			return "newpost";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleNewpost(@Validated @ModelAttribute NewPost newPost,
			BindingResult result, HttpSession session) {
		User user = (User) session.getAttribute(USER_ATTRIBUTE);
		if (result.hasErrors()) {
			return "newpost";
		}
		if (user == null) {
			return "login";
		} else {
			List<String> tagsAsList = extractTags(newPost.getTags());
			String permalink = postService.addPost(newPost.getTitle(),
					newPost.getBody(), tagsAsList, user.getUsername());
			return "redirect:/post/" + permalink;
		}
	}

	private List<String> extractTags(String tags) {
		tags = tags.replaceAll("\\s", "");
		String tagArray[] = tags.split(",");
		// let's clean it up, removing the empty string and removing dups
		ArrayList<String> cleaned = new ArrayList<String>();
		for (String tag : tagArray) {
			if (!tag.equals("") && !cleaned.contains(tag)) {
				cleaned.add(tag);
			}
		}
		return cleaned;
	}

}

package by.project.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.project.my.model.Comment;
import by.project.my.model.Post;
import by.project.my.service.PostService;
import by.project.my.validator.CommentValidator;

@Controller
@RequestMapping("/post/newcomment")
public class CommentController {

	@Autowired
	private PostService postService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new CommentValidator());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleNewComment(@Validated @ModelAttribute Comment comment,
			BindingResult result, @RequestParam String permalink) {
		Post post = postService.findPostByPermalink(permalink);
		if (post == null) {
			return "post_not_found";
		}
		if (result.hasErrors()) {
			return "redirect:/post/" + permalink;
		} else {
			postService.addPostComment(post, comment);
			return "redirect:/post/" + permalink;
		}
	}

}

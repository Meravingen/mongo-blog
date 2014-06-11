package by.project.my.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.project.my.model.Comment;

public class CommentValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[\\S]+@[\\S]+\\.[\\S]+$";

	@Override
	public boolean supports(Class<?> clazz) {
		return (Comment.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "author", "required",
				new Object[] { "Author" });
		ValidationUtils.rejectIfEmpty(errors, "body", "required",
				new Object[] { "Body" });
		ValidationUtils.rejectIfEmpty(errors, "email", "required",
				new Object[] { "Email" });

		Comment comment = (Comment) target;

		if (!errors.hasFieldErrors("email")) {
			String email = comment.getEmail();
			if (!email.matches(EMAIL_PATTERN)) {
				errors.rejectValue("emailAddress", "invalid");
			}
		}
	}

}

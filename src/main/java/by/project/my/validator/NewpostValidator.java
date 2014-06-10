package by.project.my.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.project.my.model.NewPost;

public class NewpostValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (NewPost.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title", "required",
				new Object[] { "Title" });
		ValidationUtils.rejectIfEmpty(errors, "body", "required",
				new Object[] { "Body" });
	}

}

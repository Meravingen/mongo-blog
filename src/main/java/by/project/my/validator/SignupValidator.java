package by.project.my.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.project.my.model.User;

public class SignupValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[\\S]+@[\\S]+\\.[\\S]+$";
	private static final int MINIMUM_PASSWORD_LENGTH = 6;

	@Override
	public boolean supports(Class<?> clazz) {
		return (User.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"required", new Object[] { "Username" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required", new Object[] { "Password" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required",
				new Object[] { "Email" });

		User user = (User) target;

		if (user.getPassword() != null
				&& user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
			errors.rejectValue("password", "invalid",
					new Object[] { Integer.valueOf(MINIMUM_PASSWORD_LENGTH) },
					"The password must be at least [" + MINIMUM_PASSWORD_LENGTH
							+ "] characters in length.");
		}

		if (!errors.hasFieldErrors("email")) {
			String email = user.getEmail();
			if (!email.matches(EMAIL_PATTERN)) {
				errors.rejectValue("emailAddress", "invalid");
			}
		}
	}

}

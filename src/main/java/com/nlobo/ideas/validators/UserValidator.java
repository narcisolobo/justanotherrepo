package com.nlobo.ideas.validators;

import com.nlobo.ideas.models.User;
import com.nlobo.ideas.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;
    public UserValidator(UserService userService) { this.userService = userService; }
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Match");
        }

        if (userService.emailExists(user.getEmail())) {
            errors.rejectValue("email", "Email");
        }
    }
}

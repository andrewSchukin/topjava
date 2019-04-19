package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;


@Component
public class UserValidator implements Validator {

    private UserService service;

    @Autowired
    public UserValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass) || UserTo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o.getClass().equals(User.class)) {
            User user = (User) o;
            try {
                User found = service.getByEmail(user.getEmail());
                if (!found.getId().equals(user.getId()))
                    errors.rejectValue("email", "emailError", "User with this email already exists");
            } catch (NotFoundException ex) {

            }
        } else {
            UserTo user = (UserTo) o;
            try {
                User found = service.getByEmail(user.getEmail());
                if (!found.getId().equals(user.getId()))
                    errors.rejectValue("email", "emailError", "User with this email already exists");
            } catch (NotFoundException ex) {

            }
        }

    }
}

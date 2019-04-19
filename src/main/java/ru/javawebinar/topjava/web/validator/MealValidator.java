package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Component
public class MealValidator implements Validator {

    private MealService mealService;

    @Autowired
    public MealValidator(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Meal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Meal meal = (Meal) o;

        List<Meal> userMeals = mealService.getAll(SecurityUtil.get().getId());

        for (Meal el : userMeals) {
            if (el.getDateTime().equals(meal.getDateTime())) {
                errors.rejectValue("dateTime", "dateTimeError", "Meal with this dateTime already exists");
            }
        }
    }
}

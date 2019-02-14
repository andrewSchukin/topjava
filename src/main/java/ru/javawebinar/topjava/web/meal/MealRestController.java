package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        return service.create(meal, authUserId());
    }

    public void update(Meal meal) {
        service.update(meal, authUserId());
    }

    public void delete(int id) throws NotFoundException {
        service.delete(id, authUserId());
    }

    public Collection<Meal> getAll() {
        return service.getAll();
    }

    public Meal get(int id) throws NotFoundException {
        return service.get(id, authUserId());
    }

}
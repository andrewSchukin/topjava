package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.FilterUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    @Autowired
    private FilterUtil filterUtil;

    public Meal create(Meal meal) {
        return service.create(meal, authUserId());
    }

    public void update(Meal meal, int id) {
        service.update(meal, id, authUserId());
    }

    public void delete(int id) throws NotFoundException {
        service.delete(id, authUserId());
    }

    public Collection<Meal> getAll() {
        return service.getAll();
    }

    public List<MealTo> getAllOwnMeal() {
        return MealsUtil.getWithExcess(service.getAll(authUserId(), LocalDate.MIN, LocalDate.MAX), authUserCaloriesPerDay());
    }

    public List<MealTo> getAllOwnMeal(FilterUtil filter) {
        return MealsUtil.getFilteredWithExcess(
                service.getAll(
                        authUserId(),
                        filter.getStartDate() == null ? LocalDate.MIN : filter.getStartDate(),
                        filter.getEndDate() == null ? LocalDate.MAX : filter.getEndDate()),
                authUserCaloriesPerDay(),
                filter.getStartTime() == null ? LocalTime.MIN : filter.getStartTime(),
                filter.getEndTime() == null ? LocalTime.MAX : filter.getEndTime());
    }

    public Meal get(int id) throws NotFoundException {
        return service.get(id, authUserId());
    }


    public FilterUtil getFilterUtil() {
        return filterUtil;
    }

    public void setFilterUtil(LocalDate starDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        filterUtil.setStartDate(starDate);
        filterUtil.setEndDate(endDate);
        filterUtil.setStartTime(startTime);
        filterUtil.setEndTime(endTime);
    }
}
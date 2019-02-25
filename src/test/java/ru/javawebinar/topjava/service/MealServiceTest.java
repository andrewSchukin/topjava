package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(MEAL1_USER.getId(), ADMIN_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        assertEquals(meal, MEAL1_USER);
    }


    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(MEAL1_USER.getId(), ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL3_USER, MEAL2_USER);
    }

    @Test()
    public void getBetweenDates() {
        List<Meal> mealList = service.getBetweenDates(LocalDateTime.of(2018, 01, 02, 0, 0).toLocalDate(), LocalDateTime.of(2018, 01, 02, 0, 0).toLocalDate(), ADMIN_ID);
        assertMatch(mealList, MEAL3_ADMIN);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> mealList = service.getBetweenDateTimes(LocalDateTime.of(2018, 01, 01, 3, 0), LocalDateTime.of(2018, 01, 01, 3, 15), ADMIN_ID);
        assertMatch(mealList, MEAL2_ADMIN, MEAL1_ADMIN);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), MEAL3_USER, MEAL2_USER, MEAL1_USER);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(new Meal(9999, LocalDateTime.now(), "test", 2000), USER_ID);
    }

    @Test
    public void update() {
        Meal updatedMeal = new Meal(MEAL1_USER);
        updatedMeal.setCalories(1111);
        updatedMeal.setDescription("Супер завтрак");
        service.update(updatedMeal, USER_ID);
        assertMatch(service.get(MEAL1_ID, USER_ID), updatedMeal);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotOwnMeal() {
        Meal updatedMeal = new Meal(MEAL1_USER);
        updatedMeal.setCalories(222);
        updatedMeal.setDescription("Супер завтрак");
        service.update(updatedMeal, ADMIN_ID);
        assertMatch(service.get(MEAL1_ID, ADMIN_ID), updatedMeal);
    }


    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Еда вкусная", 1000);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newMeal, MEAL3_USER, MEAL2_USER, MEAL1_USER);
    }
}
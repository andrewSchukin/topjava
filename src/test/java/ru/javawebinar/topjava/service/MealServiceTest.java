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
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;

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
        service.get(MEAL1_USER.getId(), getMeal2UserId());
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, getMeal1UserId());
        assertEquals(meal, MEAL1_USER);
    }


    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(MEAL1_USER.getId(), getMeal2UserId());
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, getMeal1UserId());
        assertMatch(service.getAll(getMeal1UserId()), MEAL3_USER, MEAL2_USER);
    }

    @Test()
    public void getBetweenDates() {
        List<Meal> mealList = service.getBetweenDates(LocalDateTime.parse("2018-02-01T00:00:00").toLocalDate(), LocalDateTime.parse("2018-02-01T00:00:00").toLocalDate(), getMeal2UserId());
        assertMatch(mealList, MEAL3_ADMIN);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> mealList = service.getBetweenDateTimes(LocalDateTime.parse("2018-01-01T03:00:00"), LocalDateTime.parse("2018-01-01T03:15:00"), getMeal2UserId());
        assertMatch(mealList, MEAL2_ADMIN, MEAL1_ADMIN);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(getMeal1UserId()), MEAL3_USER, MEAL2_USER, MEAL1_USER);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(MEAL2_ADMIN, getMeal1UserId());
    }

    @Test
    public void update() {
        Meal updatedMeal = new Meal(MEAL1_USER);
        updatedMeal.setCalories(1111);
        updatedMeal.setDescription("Супер завтрак");
        service.update(updatedMeal, getMeal1UserId());
        assertMatch(service.get(MEAL1_ID, getMeal1UserId()), updatedMeal);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Еда вкусная", 1000);
        Meal created = service.create(newMeal, getMeal1UserId());
        newMeal.setId(created.getId());
        assertMatch(service.getAll(getMeal1UserId()), newMeal, MEAL3_USER , MEAL2_USER, MEAL1_USER);
    }
}
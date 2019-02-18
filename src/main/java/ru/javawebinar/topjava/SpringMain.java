package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            //adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
            //Collection<Meal> all = mealRestController.getAll();
//            adminRestController.create(new User(null, "name1", "mail211111", "password", Role.ROLE_ADMIN));
//            adminRestController.create(new User(null, "name2", "mail2", "password", Role.ROLE_ADMIN));
//            mealRestController.getAll();
        }


    }
}

package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.base.MealServiceTest;

@ActiveProfiles("jdbc")
public class JdbcMealServiceTest extends MealServiceTest {
}

package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.base.UserServiceTest;

@ActiveProfiles("jdbc")
public class JdbcUserServiceTest extends UserServiceTest {
}

DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2018-01-01 01:00:00' , 'Завтрак', 500),
  (100000, '2018-01-01 01:15:00' , 'Завтрак1', 100),
  (100000, '2018-01-02 01:20:00' , 'Завтрак2', 200);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100001, '2018-01-01 03:00:00' , 'Обед', 700),
  (100001, '2018-01-01 03:15:00' , 'Обед2', 800),
  (100001, '2018-01-02 03:20:00' , 'Обед3', 900);
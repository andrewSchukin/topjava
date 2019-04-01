
**Работа с пользователями:**

Получение информации о пользователях:
GET
/rest/admin/users

Получение информации о конкретном пользователе:
GET
/rest/admin/users/{id}

Получения пользователя по email:
GET
/rest/admin/users/by?email=?

Добавление нового пользователя:
POST
/rest/admin/users
json object for example:
{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2019-04-01T07:26:52.845+0000","roles":["ROLE_USER","ROLE_ADMIN"],"caloriesPerDay":2000}

Удаление пользователя:
DELETE
/rest/admin/users/{id}

Обновление пользователя:
PUT
/rest/admin/users/{id}
json object for example:
{"name":"New","email":"new@gmail.com","password":"newPass","enabled":true,"registered":"2019-04-01T07:26:52.845+0000","roles":["ROLE_USER","ROLE_ADMIN"],"caloriesPerDay":2000}


**Работа с едой**

Получени всей еды залогиненного пользователя:
GET
/rest/user/meals

Получени конкретной еды залогиненного пользователя:
GET
/rest/user/meals/{id}"

Получени еды залогиненного пользователя за период (Способ 1):
GET
/rest/user/meals/between?startDateTime=?&endDateTime=?

Получени еды залогиненного пользователя за период (Способ 2):
GET
/rest/user/meals/filter?startDate=?&startTime=?&endDate=?&endTime=?

Удаление еды:
DELETE
/rest/user/meals/{id}

Обновление еды:
PUT
/rest/user/meals/{id}
{"dateTime":"2015-05-29T10:00:00","description":"Завтрак 2","calories":2000}

Добавление еды:
POST
/rest/user/meals
{"dateTime":"2015-05-29T10:00:00","description":"Завтрак 2","calories":2000}
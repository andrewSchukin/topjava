
**Работа с пользователями:**

Получение информации о пользователях:
curl -X GET \
  http://localhost:8080/topjava/rest/admin/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c3d3cf6f-760e-4b0b-801b-706cecbc14ad' \
  -H 'cache-control: no-cache'

Получение информации о конкретном пользователе:
curl -X GET \
  http://localhost:8080/topjava/rest/admin/users/100001 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 98bbe575-7e85-454b-8cda-3e651534ad6c' \
  -H 'cache-control: no-cache'

Получения пользователя по email:
curl -X GET \
  'http://localhost:8080/topjava/rest/admin/users/by?email=admin@gmail.com' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ecc6505e-4b68-48b4-8d42-8fd5fb6649d9' \
  -H 'cache-control: no-cache'

Добавление нового пользователя:
curl -X POST \
  http://localhost:8080/topjava/rest/admin/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 28a38b0f-4f00-433f-8d9a-2993c4cba838' \
  -H 'cache-control: no-cache' \
  -d '{
	"name":"New",
	"email":"new@gmail.com",
	"password":"newPass",
	"enabled":true,
	"registered":"2019-04-01T07:26:52.845+0000",
	"roles":["ROLE_USER","ROLE_ADMIN"],
	"caloriesPerDay":2000
	
}'

Удаление пользователя:
curl -X DELETE \
  http://localhost:8080/topjava/rest/admin/users/100010 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0b90d485-5d0d-4d8d-963a-275c60ca79e4' \
  -H 'cache-control: no-cache' \

Обновление пользователя:
curl -X PUT \
  http://localhost:8080/topjava/rest/admin/users/100012 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 958c2fba-51c5-4136-b7d0-060fb9387a2a' \
  -H 'cache-control: no-cache' \
  -d '{
	"name":"New",
	"email":"new@gmail.com",
	"password":"newPass1",
	"enabled":true,
	"registered":"2019-04-01T07:26:52.845+0000",
	"roles":["ROLE_USER","ROLE_ADMIN"],
	"caloriesPerDay":2000
}'


**Работа с едой**

Получени всей еды залогиненного пользователя:
curl -X GET \
  http://localhost:8080/topjava/rest/user/meals \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 373ac4c7-0951-44ef-afe0-0ea9fcf26964' \
  -H 'cache-control: no-cache'

Получени конкретной еды залогиненного пользователя:
curl -X GET \
  http://localhost:8080/topjava/rest/user/meals/100007 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f97fd0c9-07c7-4a4c-9fab-dfc3f558453c' \
  -H 'cache-control: no-cache'

Получени еды залогиненного пользователя за период:
curl -X GET \
  'http://localhost:8080/topjava/rest/user/meals/filter?startDate=2015-05-31&startTime=10:00&endDate=2015-05-31&endTime=13:00' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7e184e08-06f0-4f67-8c71-6944cee1ad58' \
  -H 'cache-control: no-cache'

Удаление еды:
curl -X DELETE \
  http://localhost:8080/topjava/rest/user/meals/100006 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7076dc0f-1778-4b4a-bd53-cc37a44dd042' \
  -H 'cache-control: no-cache'

Обновление еды:
curl -X PUT \
  http://localhost:8080/topjava/rest/user/meals/100013 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cee9daa0-09cd-458b-a7f3-aa5d751288d6' \
  -H 'cache-control: no-cache' \
  -d '{
	"dateTime":"2015-05-29T10:00:00",
	"description":"Завтрак 3",
	"calories":2000
}'

Добавление еды:
curl -X POST \
  http://localhost:8080/topjava/rest/user/meals \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f9cf0710-7feb-4300-ae92-b612ebcc5697' \
  -H 'cache-control: no-cache' \
  -d '{
		"dateTime":"2015-05-29T10:00:00",
		"description":"Завтрак 2",
		"calories":2000
	}'
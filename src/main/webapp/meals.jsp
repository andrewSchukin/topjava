<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    table {
        width: 50%; /* Ширина таблицы */
        border-collapse: collapse; /* Убираем двойные линии между ячейками */
    }
    td, th {
        padding: 4px; /* Поля в ячейках */
        border: 1px solid #999; /* Граница между ячейками */
    }
    thead {
        background: #666; /* Цвет фона строки заголовка */
        color: #fff; /* Цвет текста */
    }
    .green {
        background: lightgreen;
    }
    .red {
        background: darksalmon;
    }
</style>
<html>
<head>
    <title>
        Список еды
    </title>
</head>
<body>
    <div class="container">
        <table class="">
            <thead>
            <tr>
                <th>Id</th>
                <th>Дата/время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="list" items="${mealList}">
                <tr class="${list.isExcess() eq true ? 'red' : 'green'}">
                    <td>${list.getId()}</td>
                    <td>${list.getFormattedDateTime()}</td>
                    <td>${list.getDescription()}</td>
                    <td>${list.getCalories()}</td>
                    <td><a href="meal?action=delete&id=<c:out value="${list.getId()}"/>">Delete</a></td>
                    <td><a href="meal?action=edit&id=<c:out value="${list.getId()}"/>">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="meal?action=add">Add meal</a>
    </div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <td>Дата/время</td>
                <td>Описание</td>
                <td>Калории</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="list" items="${mealList}">
                <tr class="${list.isExcess() eq true ? 'red' : 'green'}">
                    <td>${list.getFormattedDateTime()}</td>
                    <td>${list.getDescription()}</td>
                    <td>${list.getCalories()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

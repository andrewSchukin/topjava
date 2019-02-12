<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    div {
        margin-bottom: 10px;
    }
</style>
<html>
<head>
    <title>Create and edit Meal</title>
</head>
<body>
    <form method="POST" action="meal" name="mainFrame">
        <div class="container">
            Meal Id: <input type="text" readonly="readonly" name="id" value="<c:out value="${meal.getId()}"/>"> <br/>
        </div>
        <div class="container">
            Дата время: <input type="datetime-local" name="date" value="<c:out value="${meal.getDateTime()}"/>"> <br/>
        </div>
        <div class="container">
            Описание: <input type="text" name="description" value="<c:out value="${meal.getDescription()}"/>"> <br/>
        </div>
        <div class="container">
            Калории: <input type="number" name="calories" value="<c:out value="${meal.getCalories()}"/>"> <br/>
        </div>
        <button type="submit">Accept</button>
    </form>
</body>
</html>
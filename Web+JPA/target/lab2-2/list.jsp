<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список Дней</title>
</head>
<body>

<h3>Все пользователи:</h3>(<a href="add">добавить</a>)
<ol>
    <c:forEach items="${weather}" var="day">
        <li>
            Дата - ${day.date} - Температура ${day.temperature} - Скорость ветра ${day.windSpeed}

            <a href="add?edit=${day.id}">редактировать</a> | <a href="delete?id=${day.id}">удалить</a>

        </li>
    </c:forEach>
</ol>

</body>
</html>
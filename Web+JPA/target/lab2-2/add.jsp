<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление | Редактирование</title>
</head>
<body>

<form action="add" method="post">
    <label for="name">Введите Дату:
        <input type="text" id="name" value="${weather.date}" name="date" />
    </label>  <br />
    <label for="last-name">Введите Температуру:
        <input type="text" id="last-name" value="${weather.temperature}" name="temperature" />
    </label>  <br />
    <label for="age">Введите Скорость ветра:
        <input type="text" id="age" value="${weather.windSpeed}" name="windSpeed" />
    </label>  <br />
    <input type="hidden" name="id" value="${weather.id}" />
    <input type="submit" value="Сохранить" />
</form>

</body>
</html>
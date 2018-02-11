<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="<%= request.getContextPath() %>/jsp/css/style.css" />
    <title>База данных</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/get-all-employees.js" type="text/javascript"></script>
</head>
<body>
<header>
    <jsp:include page="parts/header.jsp" />
</header>
<main>
    <section>
        <article>
            <h2>БАЗА ДАННЫХ</h2>
            <p id="allEmployees"></p>
        </article>
        <article>
            <h2>ПОИСК</h2>
            <form action="/search" method="POST">
                <label for="search1">Login: </label>
                <input id="search1" name="login" type="text"/></br>
                <label for="search2">ФИО: </label>
                <input id="search2" name="fio" type="text"/></br>
                <label for="search3">Должность: </label>
                <input id="search3"  name="department" type="text"/></br>
                <label for="search4">Город: </label>
                <input id="search4"  name="city" type="text"/></br>
                <label for="search5">Возраст от </label>
                <input id="search5"  name="ageFrom" type="number" min="1" max="999" step="1"/>
                <label for="search6"> до </label>
                <input id="search6"  name="ageTo" type="number" min="1" max="999" step="1"/></br>
                <button type="submit">Найти</button>
            </form>
        </article>
    </section>
</main>
<aside>
    <jsp:include page="parts/aside.jsp" />
</aside>
<footer>
    <jsp:include page="parts/footer.jsp" />
</footer>
</body>
</html>
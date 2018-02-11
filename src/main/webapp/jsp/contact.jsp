<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <title>Контакты</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <jsp:include page="parts/header.jsp" />
</header>
<main>
    <section>
        <article>
            <h2>Главный офис</h2>
            <p>г. Город, ул. Улица, д. 123</p>
            <p>+7(123)456-78-90</p>
            <p>Мы работаем пн-пт 10:00-19:00</p>
            <p>E-mail: <a href="mailto:mail@mail.mail">mail@mail.mail</a></p>
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
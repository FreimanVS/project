<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="userInfo" uri="/userInfo" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Главная</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <h1>Новости компании</h1>
        <article>
            <h2>ежемесячный финансовый отчёт</h2>
            <p>Благодаря старанию наших сотрудников и небольшой удаче месячная прибыль компании Company повысилась на 15%</p>
        </article>
        <article>
            <h2>спортивные успехи</h2>
            <p>Второе место на областном футбольном турнире за нашими ребятами</p>
        </article>
        <article>
            <h2>новый офис</h2>
            <p>В течение недели откроется новый офис по адресу: г. Город, пр. Проспект, д. 1</p>
        </article>
    </section>
</main>
<aside>
    <%@ include file="parts/aside.jsp"%>
</aside>
<footer>
    <%@ include file="parts/footer.jsp"%>
    <userInfo:userInfo />
</footer>
</body>
</html>
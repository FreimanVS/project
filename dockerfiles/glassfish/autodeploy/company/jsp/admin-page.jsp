<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="userInfo" uri="/userInfo" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Админская</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/admin-page-scripts.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>Скрипт для запуска:</h2>
            <p>
                <input type="text" id="scriptText" size="50"/>
            </p>
        </article>
        <article>
            <h2>Результат:</h2>
            <p>
                <textarea id="nashornResult" style="height:400px;width:600px;" disabled></textarea>
            </p>
        </article>
        <article>
            <h2>SWAGGER</h2>
            <p>
                <a href="${pageContext.request.contextPath}/jsp/swagger.html">to a swagger page</a>
            </p>
        </article>
        <article>
            <h2>PAYMENTS</h2>
            <p>
                <a href="${pageContext.request.contextPath}/jsp/calculate.jsp">to a payment page</a>
            </p>
        </article>
        <article>
            <h2>COMPARE JERSEY TO SERVLET</h2>
            <p>
                <a href="${pageContext.request.contextPath}/compare">click to find out the result</a>
            </p>
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
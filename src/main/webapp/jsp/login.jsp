<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="userInfo" uri="/userInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="<%= request.getContextPath() %>/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Вход</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <c:if test="${sessionScope.login ne null }">
            <c:out value="Hello, ${sessionScope.login}!" escapeXml="true"></c:out>
        </c:if>
        <article>
            <h2>ВХОД</h2>
            <div style="color:red;text-align: center";>${error}</div>
            <form action="${pageContext.request.contextPath}/auth" method="POST">
                <input name="login" type="text" placeholder="Имя пользователя" required="required" size="25"
                       minlength="5" maxlength="15" autofocus/>
                <input name="password" type="password" placeholder="Пароль" required="required" size="25"
                       minlength="5" maxlength="15"/>
                <button type="submit">Войти</button>
            </form>
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
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
    <%--<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>--%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="/jsp/parts/header.jsp"%>
</header>
<main>
    <section>
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal ne null}">
                <c:out value="Hello, ${pageContext.request.userPrincipal.name}!" escapeXml="true"></c:out>
                <a href = "<c:url value = "/login?logout=true"/>">выйти</a>
            </c:when>
            <c:otherwise>
                <article>
                    <h2>ВХОД</h2>
                    <div style="color:red;text-align: center";>${param.error}</div>
                    <form method="POST" action="j_security_check">
                        <input name="j_username" type="text" placeholder="Имя пользователя" required="required" size="25"
                               minlength="5" maxlength="15" autofocus/>
                        <input name="j_password" type="password" placeholder="Пароль" required="required" size="25"
                               minlength="5" maxlength="15"/>
                        <button>Войти</button>
                    </form>
                </article>
            </c:otherwise>
        </c:choose>
    </section>
</main>
<aside>
    <%@ include file="/jsp/parts/aside.jsp"%>
</aside>
<footer>
    <%@ include file="/jsp/parts/footer.jsp"%>
    <userInfo:userInfo />
</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <script src="${pageContext.request.contextPath}/jsp/js/chat.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>чат</h2>
            <p>
                <div>
                    <textarea id="messages" rows="20" cols="150" readonly="readonly" style="resize: none"></textarea>
                </div>

                <form name="message" onclick="return false;">
                    login <input name="username" type="text" id="username" size="40"/>
                    message <input name="usermsg" type="text" id="message" size="40"/>
                    <input type="button" name="submitmsg" value="send"/>
                </form>
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
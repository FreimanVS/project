<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="userInfo" uri="/userInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Админская</title>
    <%--<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>--%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/admin-page-scripts.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/get-all-performance.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>database control</h2>
            <p>
                <a href="${pageContext.request.contextPath}/jsp/data-base.jsp">database control</a>
            </p>
        </article>
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
        <%--<article>--%>
            <%--<h2>COMPARE JERSEY TO SERVLET</h2>--%>
            <%--<p>--%>
                <%--<a href="${pageContext.request.contextPath}/compare">click to find out the result</a>--%>
            <%--</p>--%>
        <%--</article>--%>
        <article>
            <p>
                <a href="${pageContext.request.contextPath}/report?report=performance&format=pdf">
                    <img src="http://www.devona.ru/upload/medialibrary/9a1/9a11a476e0fb4211ed50ebb03e591918.png"
                         alt="pdf-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${pageContext.request.contextPath}/mail?report=performance&format=pdf">
                    send pdf to email
                </a>
                <a href="${pageContext.request.contextPath}/report?report=performance&format=xml">
                    <img src="https://cdn0.iconfinder.com/data/icons/file-formats-flat-colorful-1/2048/1754_-_XML-512.png"
                         alt="xml-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${pageContext.request.contextPath}/mail?report=performance&format=xml">
                    send xml to email
                </a>
            </p>
            <h2>PERFORMANCE TABLE</h2>
            <p class="allPerformance"></p>
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
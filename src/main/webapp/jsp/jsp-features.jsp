<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="custom" uri="/tld" %>
<%@ taglib prefix="counter" uri="/custom" %>
<%@ taglib prefix="userInfo" uri="/userInfo" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>JPS features</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <h1>JSP Features</h1>
        <article>
            <h2>JSP Bean</h2>
            <p>
                <jsp:useBean id="address" class="com.freimanvs.company.jspfeatures.model.Address" />

                <%--<jsp:setProperty name="address" property="name" value="nameFromJSP" />--%>
                <%--<jsp:setProperty name="address" property="name" param="param" />--%>
                <jsp:setProperty name="address" property="*" />

                <jsp:getProperty name="address" property="name" />
            </p>
        </article>
        <article>
            <h2>JSP Tag</h2>
            <p>
                <custom:customstyle size="6">
                    THE CUSTOM TAG IS RIGHT HERE
                </custom:customstyle>
            </p>
        </article>
        <article>
            <h2>JSP Writer</h2>
            <p>
                <% pageContext.getOut().println("jsp writer"); %>
            </p>
        </article>
        <article>
            <h2>Counter</h2>
            <p>
                This page has been visited <counter:counter/> times.
            </p>
        </article>
        <article>
            <h2>User info</h2>
            <p><userInfo:userInfo /></p>
        </article>
    </section>
</main>
<aside>
    <%@ include file="parts/aside.jsp"%>
</aside>
<footer>
    <%@ include file="parts/footer.jsp"%>
</footer>
</body>
</html>
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
    <title>База данных</title>
    <%--<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>--%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/get-all-employees.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/get-all-performance.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>БАЗА ДАННЫХ</h2>
            <p>
                <a href="${pageContext.request.contextPath}/report?report=employee&format=pdf">
                <img src="http://www.devona.ru/upload/medialibrary/9a1/9a11a476e0fb4211ed50ebb03e591918.png"
                     alt="pdf-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${pageContext.request.contextPath}/mail?report=employee&format=pdf">
                    send pdf to email
                </a>
                <a href="${pageContext.request.contextPath}/report?report=employee&format=xml">
                    <img src="https://cdn0.iconfinder.com/data/icons/file-formats-flat-colorful-1/2048/1754_-_XML-512.png"
                         alt="xml-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${pageContext.request.contextPath}/mail?report=employee&format=xml">
                    send xml to email
                </a>
            </p>
            <p id="allEmployees"></p>
        </article>
        <article>
            <h2>ПОИСК</h2>
            <form action="${pageContext.request.contextPath}/search" method="POST">
                <label for="search1">Login: </label>
                <input id="search1" name="login" type="text"/>

                <label for="search2">ФИО: </label>
                <input id="search2" name="fio" type="text"/></br>

                <label for="search3">Должность: </label>
                <select id="search3" name="position">
                    <option value="">any</option>
                    <option value="worker">worker</option>
                    <option value="HR">HR</option>
                    <option value="Accounter">Accounter</option>
                    <option value="Director">Director</option>
                </select>

                <label for="search4">Город: </label>
                <select id="search4" name="city">
                    <option value="">any</option>
                    <option value="Moscow">Moscow</option>
                    <option value="Saint-Petersburg">Saint-Petersburg</option>
                    <option value="Ekaterinburg">Ekaterinburg</option>
                    <option value="NizhniiNovgorod">NizhniiNovgorod</option>
                    <option value="Saratov">Saratov</option>
                    <option value="Arhangelsk">Arhangelsk</option>
                    <option value="Astana">Astana</option>
                    <option value="Minsk">Minsk</option>
                    <option value="Berlin">Berlin</option>
                    <option value="New-York">New-York</option>
                    <option value="Ivanovo">Ivanovo</option>
                </select>

                <label for="search5">Возраст от </label>
                <input id="search5"  name="ageFrom" type="number" min="1" max="999" step="1"/>

                <label for="search6"> до </label>
                <input id="search6"  name="ageTo" type="number" min="1" max="999" step="1"/></br>

                <button type="submit">Найти</button>


            </form>
        </article>
        <article>
            <h2>Управление ролями</h2>
            <div style="color:red;text-align: center";>${noLogin}</div>
            <form action="${pageContext.request.contextPath}/admin" method="POST">
                <label for="login1">Login: </label>
                <input id="login1" name="login" type="text" required="required" size="25" minlength="5" maxlength="15"/>

                <label for="roles1">Roles: </label>
                <select id="roles1" name="roles">
                    <option value="user">user</option>
                    <option value="admin">admin</option>
                    <option value="user&admin">user & admin</option>
                </select>
                <button>Отправить</button>

            </form>
        </article>
        <article>
            <h2>Tracking</h2>
            <p>
                <form method="GET" action="${pageContext.request.contextPath}/tracking">
                    <input type="radio" name="tracking" value="on"> ON<br>
                    <input type="radio" name="tracking" value="off"> OFF<br>
                    <button>submit</button>
                </form>
            </p>
        </article>
        <article>
            <a href="${pageContext.request.contextPath}/tracking?db=true"><h2>Полная информация о tracking</h2></a>
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
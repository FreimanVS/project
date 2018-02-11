<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css" />
    <title>Админская</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/js/admin-page-scripts.js" type="text/javascript"></script>
</head>
<body>
<header>
    <jsp:include page="parts/header.jsp" />
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
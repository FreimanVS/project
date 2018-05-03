<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${fw.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${fw.contextPath}"/>
    <title>Аналитика</title>
    <#--<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>-->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js" type="text/javascript"></script>
    <script src="${fw.contextPath}/jsp/js/sidebar.js" type="text/javascript"></script>
    <script src="${fw.contextPath}/jsp/js/show-analytics.js" type="text/javascript"></script>
</head>
<body>
<header>
    <@include_page path="/jsp/parts/header.jsp"/>
</header>
<main>
    <section>
        <article>
            <h2>Analytics</h2>
            <p id="trackingdb"></p>
        </article>
    </section>
</main>
<aside>
    <@include_page path="/jsp/parts/aside.jsp"/>
</aside>
<footer>
    <@include_page path="/jsp/parts/footer.jsp"/>
</footer>
</body>
</html>
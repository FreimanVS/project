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
            <p>
                <a href="${fw.contextPath}/report?report=analytics&format=pdf">
                    <img src="http://www.devona.ru/upload/medialibrary/9a1/9a11a476e0fb4211ed50ebb03e591918.png"
                         alt="pdf-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${fw.contextPath}/mail?report=analytics&format=pdf">
                    send pdf to email
                </a>
                <a href="${fw.contextPath}/report?report=analytics&format=xml">
                    <img src="https://cdn0.iconfinder.com/data/icons/file-formats-flat-colorful-1/2048/1754_-_XML-512.png"
                         alt="xml-download" style="width:40px;height:40px;border:0;">
                </a>
                <a href="${fw.contextPath}/mail?report=analytics&format=xml">
                    send xml to email
                </a>
            </p>
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
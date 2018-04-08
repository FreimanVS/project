<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="${fw.contextPath}/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${fw.contextPath}"/>
    <title>Аналитика</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
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
        <#--<article>-->
            <#--<table>-->
                <#--<caption><b>Analytics</b></caption>-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th>id</th>-->
                    <#--<th>marker</th>-->
                    <#--<th>jsp_name</th>-->
                    <#--<th>ip</th>-->
                    <#--<th>browser_info</th>-->
                    <#--<th>client_time</th>-->
                    <#--<th>server_time</th>-->
                    <#--<th>login</th>-->
                    <#--<th>cookie</th>-->
                    <#--<th>p_id</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody>-->
                <#--<#list analytics as analytics_obj>-->
                    <#--<tr>-->
                        <#--<td>${analytics_obj.id}</td>-->
                        <#--<td>${analytics_obj.marker_name}</td>-->
                        <#--<td>${analytics_obj.jsp_name}</td>-->
                        <#--<td>${analytics_obj.ip}</td>-->
                        <#--<td>${analytics_obj.browser_info}</td>-->
                        <#--<td>${analytics_obj.client_time}</td>-->
                        <#--<td>${analytics_obj.server_time}</td>-->
                        <#--<td>${analytics_obj.login}</td>-->
                        <#--<td>${analytics_obj.cookie}</td>-->
                        <#--<td>${analytics_obj.prev_id}</td>-->
                    <#--</tr>-->
                <#--</#list>-->
                <#--<tbody>-->
                <#--<tfoot>-->
                <#--<tr>-->
                    <#--<th colspan="10">отслеживание информации по пользователям</th>-->
                <#--</tr>-->
                <#--</tfoot>-->
            <#--</table>-->
        <#--</article>-->
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
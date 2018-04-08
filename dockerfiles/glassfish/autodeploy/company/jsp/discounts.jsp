<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="userInfo" uri="/userInfo" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" media="all" type="text/css" href="<%= request.getContextPath() %>/jsp/css/style.css" />
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Скидки</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/jsp/js/sidebar.js" type="text/javascript"></script>
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>Скидка в 2 000р. на тариф «Бизнес Pro»</h2>
            <p>Получить скидку в 2 000р. на тариф «Бизнес Pro» можно при 100% предоплате работ. Мы работаем по договору,
                оплату принимаем только через банк (для юр.лиц - безналичный расчет,
                для физ.лиц - через Сбербанк, по кредитной карте или при оплате электронными
                деньгами).
            </p>
        </article>
        <article>
            <h2>Скидка в 3 000р. на тариф «Бизнес Pro Каталог»</h2>
            <p>При 100% предоплате наших услуг по тарифу «Бизнес Pro Каталог» Вы получаете скидку в размере 3 000р.
            </p>
        </article>
        <article>
            <h2>Скидка в 5 000р. на тариф «Всё включено»</h2>
            <p>При 100% предоплате работ Вы получите скидку 5 000р. Мы работаем по договору,
                оплату принимаем через банк (для также физ.лиц удобные способы оплаты).
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
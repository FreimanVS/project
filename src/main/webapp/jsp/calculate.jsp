<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
</head>
<body>
<header>
    <%@ include file="parts/header.jsp"%>
</header>
<main>
    <section>
        <article>
            <h2>дифференцированный платеж</h2>
            <p>
            <form action="${pageContext.request.contextPath}/api/bank/v1" method="POST">
                <div>
                    <input name="t" type="number" placeholder="количество периодов оплаты"/>
                </div>
                <div>
                    <input name="kr" type="number" placeholder="сумма кредита"/>
                </div>
                <div>
                    <input name="st" type="number" placeholder="процентная ставка, начисляемая на задолженность за период"/>
                </div>
                <input type="submit" value="сосчитать"/>
            </form>
            </p>

            <h2>аннуитетный платеж</h2>
            <p>
            <form action="${pageContext.request.contextPath}/api/bank/v2" method="POST">
                <div>
                    <input name="t" type="number" placeholder="количество периодов оплаты"/>
                </div>
                <div>
                    <input name="kr" type="number" placeholder="сумма кредита"/>
                </div>
                <div>
                    <input name="st" type="number" placeholder="процентная ставка, начисляемая на задолженность за период"/>
                </div>
                <input type="submit" value="сосчитать"/>
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
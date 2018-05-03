    <article>
        <div style='margin-left:40px;' >
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal ne null}">
                    <c:out value="Hello, ${pageContext.request.userPrincipal.name}!" escapeXml="true"></c:out>
                    <a href = "<c:url value = "/login?logout=true"/>">выйти</a>
                </c:when>
            </c:choose>
        </div>
    </article>
    <article>
        <h2>Курс валют</h2>
        <div id="curs">БЛОК ДЛЯ КУРСА ВАЛЮТ</div>
    </article>
    <article>
        <h2>Реклама</h2>
        <div style='margin-left:40px;' id="advertisement">БЛОК ДЛЯ РЕКЛАМЫ</div>
    </article>
    <%-- it doesn't work because of the soap service webservicex.net is offline --%>
    <%--<article>--%>
        <%--<h2>Найти города по стране</h2>--%>
        <%--<div style='margin-left:40px;'>--%>
            <%--<form method="POST" onsubmit="return false;">--%>
                <%--<input id="country" type="text" placeholder="country"/>--%>
                <%--<button id="submit_country">найти</button>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</article>--%>
    <article>
        <h2>Новости</h2>
        <div id="news">БЛОК ДЛЯ НОВОСТЕЙ</div>
    </article>
    <section>
        <h1>COMPANY</h1>
        <a href="<%= request.getContextPath() %>/"><img src="http://www.logologo.com/logos/tree-logo.jpg"
                                           alt="logo" style="width:80px;height:80px;border:0;"></a>

        <form action="https://www.google.com/search" method="GET">
            <input name="q" type="text" placeholder="поиск" size="30"/>
            <button type="submit">Найти</button>
        </form>

        <span id="phone"><b>+7(123)456-78-90</b></span>
    </section>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/">ГЛАВНАЯ</a></li>
            <li><a href="<%= request.getContextPath() %>/login">ВХОД</a></li>
            <li><a href="<%= request.getContextPath() %>/discounts">СКИДКИ</a></li>
            <li><a href="<%= request.getContextPath() %>/admin">АДМИНСКАЯ</a></li>
            <li><a href="<%= request.getContextPath() %>/contact">КОНТАКТЫ</a></li>
            <li><a href="<%= request.getContextPath() %>/chat">ЧАТ</a></li>
        </ul>
    </nav>
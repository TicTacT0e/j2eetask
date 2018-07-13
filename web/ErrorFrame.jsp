<%--
  Created by IntelliJ IDEA.
  User: tictactoe
  Date: 13/07/18
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" pageEncoding="UTF-8" session="false" %>

<html>
<head>
    <title>Error frame</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
</head>
<body>

<h2>
    <%-- exception содержит ссылку на возникшее исключение --%>

    <%-- Выводим тип исключительной ситуации --%>
    <%= exception.getClass().getName() %>
    :
    <%-- Выводим текст сообщения исключительной ситуации --%>
    <%= exception.getMessage() %>
</h2>


<% exception.printStackTrace(); %>


</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: tictactoe
  Date: 11/07/18
  Time: 07:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>MainFrame</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
</head>

<body>

<form action="<c:url value="/main"/>" method="post" >

</form>

<h3>Content</h3>

<form action="<c:url value="/bookServ"/>" method="post">
    <label for="searchField">Search field: </label>
    <input type="text" placeholder="Search..." id="searchField" name="searchField">
    <input type="submit" value="Search">
</form>

<table>
    <c:forEach var="books" items="${booksList}">
        <tr>
            <td>${books.id}</td>
            <td>${books.name}</td>
            <td>${books.autor}</td>
            <td>${books.price}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

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

<form action="<c:url value="/main"/>" method="post">


    <h3>Content</h3>


    <label for="searchField">Search field: </label>
    <input type="text" placeholder="Search..." id="searchField" name="searchField">
    <input type="submit" value="Search">


    <table>
        <c:forEach var="books" items="${booksList}">
            <tr>
                <td><input type="radio" name="booksId" value="${books.id}"></td>
                <td>${books.id}</td>
                <td>${books.name}</td>
                <td>${books.author}</td>
                <td>${books.price}</td>
            </tr>
        </c:forEach>
    </table>

    <hr>

    <table>
        <tr>
            <td><input type="submit" value="Add" name="add"/></td>
            <td><input type="submit" value="Edit" name="edit"/></td>
            <td><input type="submit" value="Delete" name="delete"/></td>
        </tr>
    </table>

</form>


</body>
</html>
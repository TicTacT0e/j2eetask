<%--
  Created by IntelliJ IDEA.
  User: tictactoe
  Date: 11/07/18
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> Edit Frame </title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
</head>

<body>

<form action="<c:url value="/bookserv"/>" method="post">

    <input type="hidden" name="newBook" value="${newBook.id}"/>
    <table>
        <tr>
            <td>ID:</td>
            <td><c:out value="${newBook.id}"/></td>
        </tr>
        <tr>
            <td><label for="booksNameField">Books name: </label></td>
            <td><input type="text" id="booksNameField"
                       name="booksNameField" value="${newBook.name}"></td>
        </tr>
        <tr>
            <td><label for="authorField">Author: </label></td>
            <td><input type="text" id="authorField" name="authorField"
                       value="${newBook.author}"></td>
        </tr>
        <tr>
            <td><label for="priceField">Price: </label></td>
            <td><input type="number" id="priceField" name="priceField" step="any"
                       value="${newBook.price}"></td>
        </tr>
    </table>

    <hr>

    <table>
        <tr>
            <td><input type="submit" value="OK" name="ok"></td>
            <td><input type="submit" value="Cancel" name="cancel"></td>
        </tr>
    </table>


</form>


</body>

</html>

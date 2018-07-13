<%--
  Created by IntelliJ IDEA.
  User: tictactoe
  Date: 13/07/18
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> Regitration </title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
</head>
<body>

<form action="<c:url value="/registration"/>" method="post">

    <input type="hidden" name="newUserId" value="${newUserId}"/>
    <table>
        <tr>
            <td><label for="usernameField">Username: </label></td>
            <td><input type="text" name="usernameField" id="usernameField"></td>
        </tr>
        <tr>
            <td><label for="passwordField">Password: </label></td>
            <td><input type="password" name="passwordField" id="passwordField"></td>
        </tr>
        <tr>
            <td><label for="repeatedPassword">Confirm password: </label></td>
            <td><input type="password" name="repeatedPassword" id="repeatedPassword"></td>
        </tr>
        <tr>
            <td><label for="emailField">EMAIL: </label></td>
            <td><input type="email" name="emailField" id="emailField"></td>
        </tr>
    </table>

    <c:out value="${errors}"/>

    <br>

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

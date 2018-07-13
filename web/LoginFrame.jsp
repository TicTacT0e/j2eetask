<%--
  Created by IntelliJ IDEA.
  User: tictactoe
  Date: 09/07/18
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> Login frame </title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
</head>
<body>

<form action="<c:url value="/login"/>" method="post">

    <label for="userField"> User: </label> <input type="text" id="userField" name="userField">
    <br>
    <label> Password: </label> <input type="password" id="passwordField" name="passwordField">
    <br>
    <input type="submit" value="OK"> <input type="submit" name="registration" value="Registration?">

</form>
</body>
</html>

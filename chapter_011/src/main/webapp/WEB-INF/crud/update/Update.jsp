<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    id = <c:out value="${user.id}"/>
    <br><br>
    <form action='${pageContext.servletContext.contextPath}/update-user' method='post'>
        name: <input type='text' value='${user.name}' name='name'/><br><br>
        login: <input type='text' value='${user.login}' name='login'/><br><br>
        email: <input type='text' value='${user.email}' name='email'/><br><br>
        <input type='hidden' name='id' value='${user.id}'/>
        <input type='submit' value='update'/>
    </form>
</body>
</html>
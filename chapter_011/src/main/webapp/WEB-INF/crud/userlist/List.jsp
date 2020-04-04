<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
    <table border="1">
        <tr><th> id </th><th> name </th><th> login </th><th> email </th><th> creation date </th></tr>
        <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.createDate}"/></td>
            <td>
                <form action='${pageContext.servletContext.contextPath}/update-user'>
                    <input type='hidden' name='id' value='${user.id}'/>
                    <input type='submit' value='update'/>
                </form>
            </td>
            <td>
                <form action='${pageContext.servletContext.contextPath}/' method='post'>
                    <input type='hidden' name='id' value='${user.id}'/>
                    <input type='submit' value='delete'/>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    <br><br>
    <form action='${pageContext.servletContext.contextPath}/create-user'>
        <input type='submit' value='Create new user'/>
    </form>
</body>
</html>
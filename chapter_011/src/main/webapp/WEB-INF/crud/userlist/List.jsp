<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserList</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table border="1">
        <tr><th>id</th><th>name</th><th>login</th><th>email</th><th>role</th><th>creation date</th><th>photo</th><th></th></tr>
        <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.createDate}"/></td>
            <td><img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="100px" height="100px"/></td>
            <td><a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a></td>
            <td>
                <c:if test="${status == 'Admin' || sessionLogin == user.login}">
                    <form action='${pageContext.servletContext.contextPath}/update-user'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <input type='submit' value='update'/>
                    </form>
                </c:if>
            </td>
            <c:if test="${status == 'Admin'}">
                <td>
                    <form action='${pageContext.servletContext.contextPath}/' method='post'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <input type='submit' value='delete'/>
                    </form>

                </td>
            </c:if>
        </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.servletContext.contextPath}/photo">Download files</a>
    <br><br>
    <c:if test="${status == 'Admin'}">
        <form action='${pageContext.servletContext.contextPath}/create-user'>
            <input type='submit' value='Create new user'/>
        </form>
        <br>
    </c:if>
    <form action='${pageContext.servletContext.contextPath}/signout' method='post'>
        <input type='submit' value='Log out'/>
    </form>
</div>
</body>
</html>
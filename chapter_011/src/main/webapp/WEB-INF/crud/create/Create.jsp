<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/create-user' method='post' enctype="multipart/form-data">
    name: <input type='text' name='name'/><br><br>
    login: <input type='text' name='login'/><br><br>
    email: <input type='text' name='email'/><br><br>
    photo: <input type='file' name='file'/><br><br>
    password: <br>
    <input type='password' name='password'/><br><br>
    role: <br>
    <select name='role'>
        <option value='Admin'>Admin</option>
        <option value='User'>User</option>
    </select>
    <br><br><br>
    <input type='submit' value='create'/>
</form>
<br>
<a href="${pageContext.servletContext.contextPath}/signout" methods="post">Log out</a>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginView</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style='background-color: red'>
        <c:out value='${error}'/>
    </div>
</c:if>
<form action='${pageContext.servletContext.contextPath}/signin' method='post'>
    login:
    <br>
    <input type='text' name='login'/><br><br>
    password:
    <br>
    <input type='password' name='password'/><br><br><br>
    <input type='submit' value='Log in'/>
</form>
</body>
</html>
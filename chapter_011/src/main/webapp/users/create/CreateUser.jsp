<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action='<%=request.getContextPath()%>/create' method='post'>
    name: <input type='text' name='name'/><br><br>
    login: <input type='text' name='login'/><br><br>
    email: <input type='text' name='email'/><br><br>
    <input type='submit' value='create'/>
</form>
</body>
</html>

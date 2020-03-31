<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <%
        User user = ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id")));
    %>
    id = <%=user.getId()%>
    <br><br>
    <form action='<%=request.getContextPath()%>/edit' method='post'>
        name: <input type='text' value='<%=user.getName()%>' name='name'/><br><br>
        login: <input type='text' value='<%=user.getLogin()%>' name='login'/><br><br>
        email: <input type='text' value='<%=user.getEmail()%>' name='email'/><br><br>
        <input type='hidden' name='id' value='<%=user.getId()%>'/>
        <input type='submit' value='update'/>
    </form>
</body>
</html>

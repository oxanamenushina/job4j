<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
    <table border="1">
        <tr><th> id </th><th> name </th><th> login </th><th> email </th><th> creation date </th></tr>
        <% for (User user : ValidateService.getInstance().findAll()) {%>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate()%></td>
            <td>
                <form action='<%=request.getContextPath()%>/users/update/UpdateUser.jsp'>
                    <input type='hidden' name='id' value='<%=user.getId()%>'/>
                    <input type='submit' value='update'/>
                </form>
            </td>
            <td>
                <form action='<%=request.getContextPath()%>/list' method='post'>
                    <input type='hidden' name='id' value='<%=user.getId()%>'/>
                    <input type='submit' value='delete'/>
                </form>
            </td>
        </tr>
        <%}%>
    </table>
    <br><br>
    <form action='<%=request.getContextPath()%>/users/create/CreateUser.jsp'>
        <input type='submit' value='Create new user'/>
    </form>
</body>
</html>
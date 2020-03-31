package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserUpdateServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = logic.findById(Integer.parseInt(req.getParameter("id")));
        PrintWriter pw = resp.getWriter();
        pw.println(this.getText(req.getContextPath(), user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        logic.update(new User(
                id == null ? -1 : Integer.parseInt(id),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email")
        ));
        resp.sendRedirect(String.format("%s/users/userlist/UserList.jsp", req.getContextPath()));
    }

    private String getText(String path, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create user</title>"
                + "</head>"
                + "<body>"
                + "id = " + user.getId() + "<br><br>"
                + "<form action='" + path + "/edit' method='post'>"
                + " name: <input type='text' value='" + user.getName() + "' name='name'/><br><br>"
                + " login: <input type='text' value='" + user.getLogin() + "' name='login'/><br><br>"
                + " email: <input type='text' value='" + user.getEmail() + "' name='email'/><br><br>"
                + "<input type='hidden' name='id' value='" + user.getId() + "'/>"
                + "<input type='submit' value='update'/>"
                + "</form>"
                + "</body>"
                + "</html>");
        return sb.toString();
    }
}
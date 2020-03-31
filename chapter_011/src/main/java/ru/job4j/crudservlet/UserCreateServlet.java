package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserCreateServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println(this.getText(req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        logic.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s/users/userlist/UserList.jsp", req.getContextPath()));
    }

    private String getText(String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create user</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + path + "/create' method='post'>"
                + " name: <input type='text' name='name'/><br><br>"
                + " login: <input type='text' name='login'/><br><br>"
                + " email: <input type='text' name='email'/><br><br>"
                + "<input type='submit' value='create'/>"
                + "</form>"
                + "</body>"
                + "</html>");
        return sb.toString();
    }
}
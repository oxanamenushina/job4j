package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * UsersServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UsersServlet extends HttpServlet {

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
        String id = req.getParameter("id");
        this.logic.delete(new User(id != null ? Integer.parseInt(id) : -1, null, null, null));
        this.doGet(req, resp);
    }

    private String getText(String path) {
        StringBuilder sb = new StringBuilder();
        List<User> users = logic.findAll();
        sb.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>List</title>"
                + "</head>"
                + "<body>"
                + "<table border=\"1\">"
                + "<tr>"
                + "<th> id </th><th> name </th><th> login </th><th> email </th><th> creation date </th>"
                + "</tr>");
        for (User user : users) {
            sb.append("<tr>"
                    + "<td> " + user.getId() + " </td>"
                    + "<td> " + user.getName() + " </td>"
                    + "<td> " + user.getLogin() + " </td>"
                    + "<td> " + user.getEmail() + " </td>"
                    + "<td> " + user.getCreateDate() + " </td>"
                    + "<td>"
                    + "<form action='" + path + "/edit' method='get'>"
                    + "<input type='hidden' name='id' value='" + user.getId() + "'/>"
                    + "<input type='submit' value='update'/>"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action='" + path + "/list' method='post'>"
                    + "<input type='hidden' name='id' value='" + user.getId() + "'/>"
                    + "<input type='submit' value='delete'/>"
                    + "</form>"
                    + "</td>"
                    + "</tr>"
            );
        }
        sb.append("</table>"
                + "<br><br>"
                + "<form action='" + path + "/create' method='get'>"
                + "<input type='submit' value='Create new user'/>"
                + "</form>"
                + "</body>"
                + "</html>");
        return sb.toString();
    }
}
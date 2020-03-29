package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * UserServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    /**
     * The actions.
     */
    private final Map<String, Function<User, Boolean>> actions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        this.actions.put("add", logic::add);
        this.actions.put("update", logic::update);
        this.actions.put("delete", logic::delete);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String action = request.getParameter("action");
        boolean result = this.actions.get(action) != null && this.actions.get(action).apply(this.getUser(request));
        writer.append("The \"").append(action).append("\" operation ");
        writer.append(result ? "was completed successfully." : "was not performed.");
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        List<User> users = this.logic.findAll();
        if (users.size() > 0) {
            users.forEach(u -> writer.append(String.format("id: %s; name: %s; login: %s; email: %s; createDate: %s%n",
                    u.getId(), u.getName(), u.getLogin(), u.getEmail(), u.getCreateDate())));
        } else {
            writer.append("There are no users.");
        }
        writer.flush();
    }

    private User getUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        return new User(id == null ? -1 : Integer.parseInt(id), request.getParameter("name"),
                request.getParameter("login"), request.getParameter("email"));
    }
}
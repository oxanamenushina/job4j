package ru.job4j.crudservlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserCreateController.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserCreateController extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/crud/create/Create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        logic.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
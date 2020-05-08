package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * UserListController.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserListController extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logic.findAll());
        req.getRequestDispatcher("/WEB-INF/crud/userlist/List.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String param = req.getParameter("id");
        int id = param != null ? Integer.parseInt(param) : -1;
        String fileName = this.logic.findById(id).getPhotoId();
        boolean result = this.logic.delete(new User(id, null, null, null, null));
        if (result && fileName != null && logic.findAll().stream().noneMatch(u -> fileName.equals(u.getPhotoId()))) {
            new File("images" + File.separator + fileName).delete();
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
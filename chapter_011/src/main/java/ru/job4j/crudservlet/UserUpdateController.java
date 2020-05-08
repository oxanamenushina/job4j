package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserUpdateController.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateController extends HttpServlet {

    /**
     * The instance of ValidateService class.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/crud/update/Update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletConfig().getServletContext();
        try {
            RequestHandler rh = new RequestHandler(req, servletContext);
            User user = rh.setParameters();
            if (logic.update(user)) {
                rh.writeFile();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
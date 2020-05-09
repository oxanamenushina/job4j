package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * RequestHandler.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RequestHandler {

    /**
     * The User instance.
     */
    private final User user = new User();

    /**
     * The list of FileItems.
     */
    private final List<FileItem> items;

    public RequestHandler(HttpServletRequest req, ServletContext servletContext) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        this.items = upload.parseRequest(req);
    }

    /**
     * The method sets parameters received from the request to the user.
     * @return user.
     */
    public User setParameters() {
        Actions actions = new Actions(this.user);
        for (FileItem item : items) {
            if (!item.isFormField()) {
                String fn = item.getName().substring(item.getName().lastIndexOf(File.separator) + 1);
                actions.getAction(item.getFieldName()).accept(fn);
            } else {
                String key = item.getFieldName();
                if (!"id".equals(key) && !"role".equals(key)) {
                    actions.getAction(key).accept(item.getString());
                } else {
                    this.setNonString(item);
                }
            }
        }
        return this.user;
    }

    /**
     * The method writes the file received from the request.
     */
    public void writeFile() {
        File folder = new File("images");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String[] files = folder.list();
        if (user.getPhotoId() != null && files != null && Arrays.stream(files).noneMatch(f -> f.equals(user.getPhotoId()))) {
            File file = new File(folder + File.separator + this.user.getPhotoId());
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(items.stream().filter(f -> !f.isFormField()).findFirst().get().getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method sets non-string parameters
     * received from the request to the user.
     * @param item item.
     */
    private void setNonString(FileItem item) {
        if ("id".equals(item.getFieldName())) {
            String id = item.getString();
            this.user.setId(id == null ? -1 : Integer.parseInt(id));
        } else {
            this.user.setRole(Role.valueOf(item.getString()));
        }
    }

    /**
     * Class Actions.
     */
    public class Actions {

        /**
         * Actions.
         */
        private final Map<String, Consumer<String>> actions = new HashMap<>();

        public Actions(User user) {
            this.actions.put("name", user::setName);
            this.actions.put("login", user::setLogin);
            this.actions.put("password", user::setPassword);
            this.actions.put("email", user::setEmail);
            this.actions.put("file", user::setPhotoId);
        }

        /**
         * The method returns a setter for the specified key.
         * @param key key.
         * @return a setter for the specified key.
         */
        public Consumer<String> getAction(String key) {
            return this.actions.get(key);
        }
    }
}
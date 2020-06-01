package ru.job4j.cinema;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * HallServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class HallServlet extends HttpServlet {

    /**
     * An instance of ValidateService class.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Place> places = this.validate.getPlaces();
        String result = new Gson().toJson(places);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.print(result);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader json = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = json.readLine()) != null) {
            sb.append(str);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() { }.getType();
        Map<String, String> map = gson.fromJson(sb.toString(), type);
        Account acc = new Account(map.get("name"), map.get("phone"),
                new Place(Integer.parseInt(map.get("row")), Integer.parseInt(map.get("number"))));
        boolean result = validate.addAccount(acc);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(result ? "success" : "failure");
        writer.flush();
    }
}
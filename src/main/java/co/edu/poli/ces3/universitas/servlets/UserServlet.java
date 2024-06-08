package co.edu.poli.ces3.universitas.servlets;


import co.edu.poli.ces3.universitas.database.dao.User;
import co.edu.poli.ces3.universitas.database.repositories.UserRepository;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "userServlet", value = "/api/user")
public class UserServlet extends MyServlet {

    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init(){
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        UserRepository repo = new UserRepository();
        try {
            out.print(gson.toJson(repo.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        JsonObject userUpdate = this.getParamsFromBody(req);
        UserRepository repo = new UserRepository();
        try {
            User user = repo.update(userUpdate, id);
            out.println(gson.toJson(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println("{\"message\": \"Hola!!!!\"}");
        out.flush();
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println("{\"message\": \"Hola desde patch!!!!\"}");
        out.flush();
    }
}

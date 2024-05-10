package co.edu.poli.ces3.universitas.servlets;


import co.edu.poli.ces3.universitas.database.dao.User;
import co.edu.poli.ces3.universitas.database.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "userServlet", value = "/api/user")
public class UserServlet extends HttpServlet {

    public void init(){
        System.out.println("Ingreso al init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<h1>Users</h1><br/>");

        UserRepository repo = new UserRepository();

        out.print("<table border=1>");
        out.print("<thead>" +
                "<tr>" +
                "<th>Nombre</th>" +
                "<th>Apellido</th>" +
                "</tr>" +
                "</thead>");
        out.print("<tbody>");
        try {
            for (User x: repo.get()) {
                out.print("<tr>" +
                        "<td>"+x.getName()+"</td>" +
                        "<td>"+x.getLastname()+"</td>" +
                        "</tr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.print("</tbody>");

        out.print("</table>");


        out.flush();
    }
}

package servlet;


import model.User;
import service.UserService;
import service.UserServiceHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    UserServiceHibernate userServiceHibernate = UserServiceHibernate.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long reqID = Long.parseLong(req.getParameter("id"));

        User userToDelete = userServiceHibernate.getUserById(reqID);
        req.setAttribute("user", userToDelete);
        req.getServletContext().getRequestDispatcher("/jsp/read.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long reqId = Long.parseLong(req.getParameter("id"));
        userServiceHibernate.deleteUserById(reqId);
        resp.sendRedirect("read");
        ;

    }

}

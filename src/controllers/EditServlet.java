package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Tasks t = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("tasks", t);
        request.setAttribute("_token", request.getSession().getId());

        request.getSession().setAttribute("tasks_id", t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Task/edit.jsp");
        rd.forward(request, response);
    }
}
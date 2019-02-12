package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.data.database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if (action.equals("add")) {
            req.getRequestDispatcher("meal.jsp").forward(req, resp);
        } else if (action.equals("edit") && id != null && !id.isEmpty()) {
            req.setAttribute("meal", database.getMealById(Integer.valueOf(id)));
            req.getRequestDispatcher("meal.jsp").forward(req, resp);
        } else if (action.equals("delete") && id != null && !id.isEmpty()) {
            database.deleteMeal(Integer.valueOf(id));
            resp.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mealId = req.getParameter("id");
        if (mealId==null || mealId.isEmpty()) {
            database.addMeal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("description"), Integer.valueOf(req.getParameter("calories")));
        } else {
            database.editMeal(Integer.valueOf(mealId), LocalDateTime.parse(req.getParameter("date")), req.getParameter("description"), Integer.valueOf(req.getParameter("calories")));
        }
        resp.sendRedirect("meals");
    }
}

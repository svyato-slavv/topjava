package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/listMeal.jsp";
    private final MealDAO mealDAO;

    private static final Logger log = getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;

    public MealServlet() {
        super();
        mealDAO = new MealDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDAO.delete(mealId);
            forward = LIST_MEAL;
            List<MealTo> listMealTo = MealsUtil.filteredByStreams(mealDAO.getListMeal(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY);
            request.setAttribute("listMealTo", listMealTo);
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.show(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEAL;
            List<MealTo> listMealTo = MealsUtil.filteredByStreams(mealDAO.getListMeal(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY);
            request.setAttribute("listMealTo", listMealTo);
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealId = request.getParameter("id");
        if (mealId == null || mealId.isEmpty()) {
            mealDAO.save(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            mealDAO.update(Integer.parseInt(mealId), meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        List<MealTo> listMealTo = MealsUtil.filteredByStreams(mealDAO.getListMeal(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY);
        request.setAttribute("listMealTo", listMealTo);
        view.forward(request, response);
    }
}

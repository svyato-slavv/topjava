package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealDAO implements MealRepo {
    List<Meal> listMeal;
    private static int MEAL_COUNT;

    {
        listMeal = new CopyOnWriteArrayList();
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        listMeal.add(new Meal(++MEAL_COUNT, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }


    public List<Meal> getListMeal() {
        return listMeal;
    }

    public Meal show(int id) {
        return listMeal.stream().filter(meal -> meal.getId() == id).findAny().orElse(null);
    }

    public void save(Meal meal) {
        listMeal.add(new Meal(++MEAL_COUNT, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    public void update(Integer id, Meal updatedMeal) {
        Meal mealToBeUpdated = show(id);
        mealToBeUpdated.setDateTime(updatedMeal.getDateTime());
        mealToBeUpdated.setDescription(updatedMeal.getDescription());
        mealToBeUpdated.setCalories(updatedMeal.getCalories());
    }

    public void delete(int id) {
        listMeal.removeIf(m -> m.getId() == id);
    }
}

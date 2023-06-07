package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepo {
    List<Meal> getListMeal();

    Meal show(int id);

    void save(Meal meal);

    void update(Integer id, Meal updatedMeal);

    void delete(int id);

}

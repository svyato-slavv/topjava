package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

// TODO add userId
public interface MealRepository {
    // null if updated meal does not belong to userId
    Meal save(Meal meal,int userId);

    // false if meal does not belong to userId
    boolean delete(int id,int userId);

    // null if meal does not belong to userId
    Meal get(int id,int userId);

    // ORDERED dateTime desc
    Collection<Meal> getAll(int userId);
}
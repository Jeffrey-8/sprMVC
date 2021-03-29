package ru.fonin.mvc.dao;

import ru.fonin.mvc.models.User;

import java.util.List;

public interface UserDao extends CrudDao<User>{
    List<User> findByFistName(String firstName);
}

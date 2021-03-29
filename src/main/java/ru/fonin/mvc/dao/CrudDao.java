package ru.fonin.mvc.dao;

import java.util.List;
import java.util.Optional;

public  interface CrudDao <T> {
    Optional<T> find(Long id);
    void save(T model);
    void delete(T model);
    void update(T model);

    List<T> findAll();

}

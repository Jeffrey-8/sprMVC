package ru.fonin.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fonin.mvc.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByFirstName(String firstName);
}

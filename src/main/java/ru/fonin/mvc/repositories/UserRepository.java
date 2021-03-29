package ru.fonin.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fonin.mvc.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

}

package ru.fonin.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fonin.mvc.models.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
     List<Car> findAllByOwner_FirstName(String firstName);
}

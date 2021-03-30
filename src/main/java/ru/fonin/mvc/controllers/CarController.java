package ru.fonin.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fonin.mvc.models.Car;
import ru.fonin.mvc.repositories.CarRepository;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping(path = "/cars",method = RequestMethod.GET)
    @ResponseBody
    public String getCarsByFirstName(@RequestParam(required = false, value = "first_name")String firstName){
        List<Car> cars;
        if(firstName != null){
            cars=carRepository.findAllByOwner_FirstName(firstName);
        } else{
            cars=carRepository.findAll();
        }
        return cars.toString();
    }

}

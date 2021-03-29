package ru.fonin.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.fonin.mvc.dao.UserDao;
import ru.fonin.mvc.forms.UserForm;
import ru.fonin.mvc.models.User;
import ru.fonin.mvc.repositories.UserRepository;

import java.util.List;

@Controller
public class UsersWithJpaController {

    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/jpa/users",method = RequestMethod.GET)
    public ModelAndView getUsers(){
        List<User> users = userRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("user");
//        modelAndView.
        modelAndView.addObject("usersFromServer",users);
        return modelAndView;
    }
    @RequestMapping(path = "/jpa/users", method = RequestMethod.POST)
    public String AddUser(UserForm userForm){
        userRepository.save(User.from(userForm));
        return "redirect:/jpa/users";
    }

}

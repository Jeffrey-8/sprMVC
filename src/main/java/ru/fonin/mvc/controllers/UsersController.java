package ru.fonin.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import ru.fonin.mvc.dao.UserDao;
import ru.fonin.mvc.forms.UserForm;
import ru.fonin.mvc.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class UsersController {

    @Autowired
    private UserDao usersDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@RequestParam(value = "first_name", required = false) String firstName) {
        List<User> users = null;

        if (firstName != null) {
            users = usersDao.findByFistName(firstName);
        } else {
            users = usersDao.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path="/users/{user-id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("user-id") Long userId){
       Optional<User> userCandidate = usersDao.find(userId);
       ModelAndView modelAndView=new ModelAndView("user");
       // до лямбда:
//       if (userCandidate.isPresent()){
//       modelAndView.addObject("usersFromServer", Collections.singletonList(userCandidate.get()));
//       }
        // после:
        userCandidate.ifPresent(user -> modelAndView.addObject("usersFromServer", Collections.singletonList(user)));
       return modelAndView;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(UserForm userForm) {
//        System.out.println(userForm);

        User newUser= User.from(userForm);
        usersDao.save(newUser);

        return "redirect:/users";
    }




}

package ru.fonin.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.fonin.mvc.dao.UserDao;
import ru.fonin.mvc.dao.UsersDaoJdbcTemplateImpl;
import ru.fonin.mvc.models.User;

import java.util.List;

public class UserControllerSimple implements Controller {

    @Autowired
    private UserDao usersDao;// тк только одна реализация UsersDao он не запутаетсяи возьмет нужную т
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        if (httpServletRequest.getMethod().equals("GET")){
//            usersDao = new UsersDaoJdbcTemplateImpl();
            List<User> users = usersDao.findAll();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user");// путь не нужен только название jsp
            modelAndView.addObject("usersFromServer",users);// все согласно схеме MVC в заметках
            return modelAndView;
        }
        return null;
    }
}

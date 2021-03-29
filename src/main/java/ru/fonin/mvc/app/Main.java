package ru.fonin.mvc.app;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.fonin.mvc.dao.UserDao;
import ru.fonin.mvc.dao.UsersDaoJdbcTemplateImpl;
import ru.fonin.mvc.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args)
    {

        try {
            DriverManagerDataSource dataSource= new DriverManagerDataSource();
            Properties properties= new Properties();
            properties.load(new FileInputStream("/Users/romanfonin/Documents/sprMVC/src/main/resources/dbMAC.properties"));

            String dbUrl= properties.getProperty("db.url");
            String dbUsername= properties.getProperty("db.username");
            String dbPassword= properties.getProperty("db.password");
            String driverClassName=properties.getProperty("db.driverClassName");

            dataSource.setUrl(dbUrl);
            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setDriverClassName(driverClassName);
            Class.forName(driverClassName);

            dataSource.setConnectionProperties(properties);
            UserDao userDao = new UsersDaoJdbcTemplateImpl(dataSource);
            List<User> users=userDao.findAll();
System.out.println(users);
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException(e);
        }
    }



}

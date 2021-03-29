package ru.fonin.mvc.dao;

//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import com.mysql.cj.result.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.fonin.mvc.models.Car;
import ru.fonin.mvc.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;


@Component
public class UsersDaoJdbcTemplateImpl implements UserDao {
    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedTemplate;


    //language=SQL
    private String SQL_SELECT_ALL = "SELECT * FROM froschema.users";
    //language=SQL
    private String SQL_SELECT_BY_ID = "SELECT * FROM froschema.users WHERE id=?";
    //language=SQL
    private final String SQL_SELECT_ALL_BY_FIRSTNAME="SELECT * FROM froschema.users WHERE first_name=?";
    //language=SQL
    private String SQL_SELECT_USERS_WITH_CARS=
//            "SELECT * FROM froschema.users LEFT JOIN froschema.car ON users.id = car.id_owner WHERE users.id=?";
    "SELECT users.*,car.id as car_id, car.model FROM froschema.users LEFT JOIN froschema.car ON users.id = car.id_owner WHERE  users.id=?";

    //language=SQL
    private String SQL_SELECT_USERS_BY_ID ="SELECT * FROM froschema.users WHERE id = :id";

    //language=SQL
    private String SQL_INSERT_USER="INSERT INTO users(first_name, last_name) VALUES (:firstName,:lastName)";


    private Map<Long,User> usersMap= new HashMap<>();

    @Autowired
    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
//        this.template = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<User> usersRowMapperWithoutCars = (resultSet, i) ->
         User.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .age(resultSet.getInt("age")).build();

    private RowMapper<User> userRowMapper
            = (ResultSet resultSet, int i) -> {
        Long id = resultSet.getLong("id");

        if (!usersMap.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            User user = new User(id, firstName, lastName,5, new ArrayList<>());
            usersMap.put(id, user);
        }

        Car car = new Car(resultSet.getLong("car_id"),
                resultSet.getString("model"), usersMap.get(id));

        usersMap.get(id).getCars().add(car);

        return usersMap.get(id);
    };

    @Override
    public Optional find(Long id) {
//         template.query(SQL_SELECT_USERS_BY_ID,usersRowMapperWithoutCars,id);
////        return usersMap.get(id);
//        if (usersMap.containsKey(id)){
//            return Optional.of(usersMap.get(id));
//        } else return Optional.empty();
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        List<User> result = namedTemplate.query(SQL_SELECT_USERS_BY_ID,params,usersRowMapperWithoutCars);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public void save(User model) {
        Map<String,Object> params=new HashMap<>();
        params.put("firstName",model.getFirstName());
        params.put("lastName",model.getLastName());
        namedTemplate.update(SQL_INSERT_USER,params);
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public List findAll() {
        return namedTemplate.query(SQL_SELECT_ALL, usersRowMapperWithoutCars);
    }

    @Override
    public List findByFistName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRSTNAME,usersRowMapperWithoutCars,firstName);
    }
}

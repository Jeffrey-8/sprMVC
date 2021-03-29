package ru.fonin.mvc.models;

import lombok.*;
import ru.fonin.mvc.forms.UserForm;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @OneToMany( mappedBy = "owner")
    List <Car> cars;

    public static User from(UserForm userForm){
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
    }
}

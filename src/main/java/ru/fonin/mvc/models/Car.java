package ru.fonin.mvc.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude ="owner")

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

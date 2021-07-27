package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Student extends User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private Long id;
    @OneToMany(mappedBy = "student")
    private Set<CourseRegistrationStudent> courseRegistrations;
}

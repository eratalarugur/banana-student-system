package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Teacher extends User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private Long id;

    @OneToMany(mappedBy = "teacher")
    private Set<CourseRegistrationTeacher> courseRegistrations;
}

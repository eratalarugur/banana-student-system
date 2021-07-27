package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @OneToOne(mappedBy = "course")
    private CourseRegistrationTeacher teacher;
    @NonNull
    private String courseCode;
    @OneToMany(mappedBy = "course")
    private Set<CourseRegistrationStudent> students;
}

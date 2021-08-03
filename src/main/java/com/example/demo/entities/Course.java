package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Course {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String courseCode;

    @ManyToMany(mappedBy = "registeredCourses")
    @JsonIgnore
    private List<Student> students;
}

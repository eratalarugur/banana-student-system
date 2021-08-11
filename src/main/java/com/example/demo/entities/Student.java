package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Student {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String birthday;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String picture;

    @NonNull
    private String city;

    @ManyToMany
    @JoinTable(
            name = "course_registered",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> registeredCourses;


}

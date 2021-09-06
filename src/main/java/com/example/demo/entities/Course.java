package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String courseCode;

    private String description;

    private String image;

    @ManyToMany(mappedBy = "registeredCourses",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students;

    @ManyToMany(mappedBy = "assignedCourses", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    //@JsonManagedReference
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments;

/*    @OneToMany(mappedBy = "course")
    private List<Chat> chats;*/

}

package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_registration_student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseRegistrationStudent {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    double grade;

}

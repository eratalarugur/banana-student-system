package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_registration_teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseRegistrationTeacher {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

}

package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Chat {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private long id;


    private String message;


    private Date postDate;

/*    @ManyToOne
    @JsonIgnore
    private Course course;*/

/*    @ManyToOne
    @JsonIgnore
    private Student student;*/

    private Long studentId;

    private Long courseId;
}

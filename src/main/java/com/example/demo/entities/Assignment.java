package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Assignment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Id
    private long id;

    @NonNull
    private String title;

    @NonNull
    private double percentage;

    @NonNull
    private String beginDate;
    
    @NonNull
    private String endDate;

    private String grade;

    private boolean submissionStatus;

    @ManyToOne
    @JsonIgnore
    private Course course;
}

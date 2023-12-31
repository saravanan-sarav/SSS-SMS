package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    public static final String TAMIL = "TAMIL";
    public static final String ENGLISH = "ENGLISH";
    public static final String MATHEMATICS = "MATHEMATICS";
    public static final String SCIENCE = "SCIENCE";
    public static final String SOCIAL = "SOCIAL";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Teacher> teacher;

    @OneToMany(mappedBy = "subjectAssignment")
    private List<Assignment> assignments;
}

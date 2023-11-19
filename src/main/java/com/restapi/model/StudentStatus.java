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
public class StudentStatus {

    public static final String ACTIVE = "ACTIVE";
    public static final String DROPOUT = "DROPOUT";
    public static final String GRADUATED = "GRADUATED";
    public static final String PENDING = "PENDING";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "studentStatus")
    private List<Student> students;
}

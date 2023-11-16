package com.restapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassRoom {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String standard;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private AppUser teacherUser;

    @OneToMany(mappedBy = "classRoom")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "classRoom")
    private List<Assignment> assignments = new ArrayList<>();





}

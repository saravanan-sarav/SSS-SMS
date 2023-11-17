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
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "std_id", referencedColumnName = "id")
    private ClassStandard classStandard;

    @OneToOne
    @JoinColumn(name = "teacher_user_id", referencedColumnName = "id")
    private AppUser teacherUserClassRoom;


    @ManyToOne
    @JoinColumn(name = "tamil_teacher_user_id", referencedColumnName = "id")
    private AppUser tamilTeacherUser;

    @ManyToOne
    @JoinColumn(name = "english_teacher_user_id", referencedColumnName = "id")
    private AppUser englishTeacherUser;

    @ManyToOne
    @JoinColumn(name = "maths_teacher_user_id", referencedColumnName = "id")
    private AppUser mathsTeacherUser;

    @ManyToOne
    @JoinColumn(name = "science_teacher_user_id", referencedColumnName = "id")
    private AppUser scienceTeacherUser;

    @ManyToOne
    @JoinColumn(name = "social_teacher_user_id", referencedColumnName = "id")
    private AppUser socialTeacherUser;


    @OneToMany(mappedBy = "classRoom")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "classRoom")
    private List<Assignment> assignments = new ArrayList<>();

}

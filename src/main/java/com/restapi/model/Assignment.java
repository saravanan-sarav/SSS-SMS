package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(nullable = false, length = 100)
    private LocalDate deadline;

    @Column(nullable = false)
    private Integer totalGrade;

    @Column(nullable = false)
    private Integer minScore;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "ass_type_id", referencedColumnName = "id",nullable = false)
    private AssignmentType assignmentType;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id",nullable = false)
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "teacher_user_id", referencedColumnName = "id")
    private AppUser teacherUserAssignment;

    @ManyToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id",nullable = false)
    private Subject subjectAssignment;

    @OneToMany(mappedBy = "assignmentForGrade")
    private List<AssignmentGrade> assignmentGradeList = new ArrayList<>();
}

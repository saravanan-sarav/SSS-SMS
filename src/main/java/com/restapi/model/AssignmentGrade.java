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
public class AssignmentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer marksObtained;

    @Column(nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "student_user_id",referencedColumnName = "id")
    private AppUser studentUserAssignmentGrade;

    @ManyToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    private Assignment assignmentForGrade;


}

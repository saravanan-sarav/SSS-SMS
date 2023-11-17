package com.restapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer marksObtained = 0;

    @OneToOne
    @JoinColumn(name = "student_user_id",referencedColumnName = "id")
    private AppUser studentUserAssignmentGrade;

    @OneToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    private Assignment assignment;


}

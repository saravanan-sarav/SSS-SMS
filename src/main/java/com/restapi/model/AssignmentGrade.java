package com.restapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AssignmentGrade {
    @Id
    @GeneratedValue
    private Long id;

    private Integer marksObtained = 0;

    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private AppUser studentUser;

    @OneToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    private Assignment assignment;


}

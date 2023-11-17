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
public class AssignmentType {

    public static final String UNIT_TEST = "UNIT_TEST";
    public static final String MONTHLY_TEST = "MONTHLY_TEST";
    public static final String QUATERLY_EXAM = "QUATERLY_EXAM";
    public static final String HALFYEARLY_EXAM = "HALFYEARLY_EXAM";
    public static final String ANNUAL_EXAM = "ANNUAL_EXAM";
    public static final String CLASS_TEST = "CLASS_TEST";
    public static final String ASSIGNMENT = "ASSIGNMENT";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 2, message = "assignment needed")
    private String type;

    @OneToMany(mappedBy = "assignmentType")
    private List<Assignment> assignment = new ArrayList<>();
}

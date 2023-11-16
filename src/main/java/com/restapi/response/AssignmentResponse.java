package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssignmentResponse {
    private Long assignmentId;
    private Long classId;
    private Long assignmentTypeId;
    private String assignmentName;
    private String classStandard;
    private LocalDate createdDate;
    private LocalDate deadline;
    private String totalGrade;
}

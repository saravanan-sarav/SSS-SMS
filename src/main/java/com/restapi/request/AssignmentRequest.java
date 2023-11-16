package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    private Long teacherUserId;
    private Long assignmentId;
    private Long classId;
    private Long assignmentTypeId;
    private LocalDate deadline;
    private String totalGrade;

}

package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    @NotEmpty
    private Long teacherUserId;
    @NotEmpty
    private Long assignmentId;
    @NotEmpty
    private Long classId;
    @NotEmpty
    private Long subjectId;
    @NotEmpty
    private Long assignmentTypeId;
    @NotEmpty
    private LocalDate deadline;
    @NotEmpty
    private Integer totalGrade;
    @NotEmpty
    private Integer minScore;
    @NotEmpty
    @Size(max = 300, message = "Text exceeds 300 character limit")
    private String comments;
}


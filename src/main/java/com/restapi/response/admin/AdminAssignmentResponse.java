package com.restapi.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminAssignmentResponse {
    private Long assignmentId;
    private String assignmentName;
    private String className;
    private String Subject;
    private Long teacherId;
    private String teacherName;
    private String teacherPhoneNumber;
    private LocalDate createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deadline;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Integer totalGrade;
    private Integer minScore;
}

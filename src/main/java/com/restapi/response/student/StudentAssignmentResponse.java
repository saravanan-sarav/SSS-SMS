package com.restapi.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignmentResponse {
    private Long assignmentId;
    private String assignmentType;
    private String comments;
    private String subjects;
    private LocalDate createdDate;
    private LocalDate DueDate;
    private String submitComments;
    private Long obtainedMark;
    private Long totalMark;
    private Long minMark;
}

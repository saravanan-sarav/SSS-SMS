package com.restapi.request.assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAssignmentMarkEntry {
    private Long assignmentGradeId;
    private Long studentUserId;
    private Long assignmentId;
    private String comments;
    private Integer markObtained;
}

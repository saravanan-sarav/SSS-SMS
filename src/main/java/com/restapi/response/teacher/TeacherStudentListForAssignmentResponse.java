package com.restapi.response.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStudentListForAssignmentResponse {
    private Long studentUserid;
    private Long assignmentGradeId;
    private String firstName;
    private String lastName;
    private String comments;
    private Integer studentMark;
    private Integer totalMark;
}

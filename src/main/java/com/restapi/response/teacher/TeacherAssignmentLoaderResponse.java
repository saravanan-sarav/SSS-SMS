package com.restapi.response.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAssignmentLoaderResponse {
    private Long assignmentId;
    private String assignmentName;
    private String className;
    private String teacherName;
    private String subjectName;
    private LocalDate dueDate;
    private Integer totalMark;
    private Integer minScore;

}

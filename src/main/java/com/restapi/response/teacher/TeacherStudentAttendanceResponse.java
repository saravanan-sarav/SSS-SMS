package com.restapi.response.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherStudentAttendanceResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String className;
    private LocalDate date;
}

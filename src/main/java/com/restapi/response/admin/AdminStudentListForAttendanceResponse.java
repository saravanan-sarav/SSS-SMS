package com.restapi.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStudentListForAttendanceResponse {
    private Long studentId;
    private String firstName;
    private String lastName;
    private Long classId;
    private String standard;
}

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
public class TeacherClassRoomResponse {
    private Long studentId;
    private String studentName;
    private String className;
    private Long parentId;
    private String motherName;
    private String motherPhoneNumber;
    private String fatherName;
    private String fatherPhoneNumber;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
}

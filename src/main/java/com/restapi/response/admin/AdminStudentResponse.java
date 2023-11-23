package com.restapi.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStudentResponse {
    private Long id;
    private Long studentUserId;
    private String firstName;
    private String lastName;
    private String className;
    private String gender;
    private String fatherName;
    private String MotherName;
    private String phoneNumber;
    private Long studentStatusId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfJoin;
}

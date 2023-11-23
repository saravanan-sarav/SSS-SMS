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
public class TeacherProfileResponse {
    private Long teacherUserId;
    private Long addressId;
    private String teacherUsername;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String subjectName;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private Long pincode;
}

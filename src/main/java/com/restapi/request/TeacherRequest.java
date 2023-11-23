package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private Long teacherUserId;
    private Long addressId;
    private String teacherUsername;
    private String teacherPassword;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private Long subjectId;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private Long pincode;
    private String gender;
}

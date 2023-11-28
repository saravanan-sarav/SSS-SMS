package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherRequest {
    private Long teacherUserId;
    private Long addressId;
    private String teacherUsername;
    private String teacherPassword;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private Long subjectId;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private Long pincode;
    private String gender;
}



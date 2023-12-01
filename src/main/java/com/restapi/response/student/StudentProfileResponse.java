package com.restapi.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileResponse {
    private Long studentId;
    private Long addressId;
    private String studentFirstName;
    private String studentLastName;
    private String classStandard;
    private String gender;
    private String studentStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private Long pincode;
}

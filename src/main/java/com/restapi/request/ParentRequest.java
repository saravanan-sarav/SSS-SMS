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
public class ParentRequest {
    private Long parentUserId;
    private Long studentUserId;
    private Long addressId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Long classId;
    private String motherName;
    private String motherPhoneNumber;
    private String motherOccupation;
    private String fatherName;
    private String fatherPhoneNumber;
    private String fatherOccupation;
    private String email;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private String pincode;
    private String studentUsername;
    private String studentPassword;
    private String parentUsername;
    private String parentPassword;
}

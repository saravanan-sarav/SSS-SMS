package com.restapi.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentParentProfileResponse {
    private Long parentId;
    private String fatherName;
    private String fatherOccupation;
    private String fatherPhoneNumber;
    private String motherName;
    private String motherOccupation;
    private String motherPhoneNumber;
    private String email;
    private String doorNum;
    private String street;
    private String addrLine;
    private String city;
    private String state;
    private Long pincode;

}

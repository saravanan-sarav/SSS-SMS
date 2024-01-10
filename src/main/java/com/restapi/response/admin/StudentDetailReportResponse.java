package com.restapi.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailReportResponse {
    private String name;
    private String className;
    private Long classId;
    private String status;
    private String joinedDate;
    private Long studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String motherName;
    private String fatherName;
    private String motherPhoneNumber;
    private String fatherPhoneNumber;
    private String motherOccupation;
    private String fatherOccupation;
    private String doorNumber;
    private String street;
    private String addressLine;
    private String city;
    private String state;
    private Long pinCode;
    private int assignmentsAssigned;
    private int assignmentsCompleted;
    private int assignmentsPassed;
}

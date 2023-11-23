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
public class AdminParentResponse {
    private Long id;
    private Long studentId;
    private String StudentName;
    private String className;
    private Long parentId;
    private String MotherName;
    private String FatherName;
    private String phoneNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate dateOfJoin;
    private String studentStatus;

}


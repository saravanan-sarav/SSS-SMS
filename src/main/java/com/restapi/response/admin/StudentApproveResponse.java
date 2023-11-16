package com.restapi.response.admin;

import com.restapi.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentApproveResponse {
    private Long studentUserId;
    private String studentUsername;
    private Long studentClassId;
    private Long studentStatusId;
    private String studentName;
}

package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String username;
    private String name;
    private String role;
    private Long teacherClassId;
    private Long subjectId;
    private Long studentId;
    private Long studentClassId;
    private Long parentId;
}

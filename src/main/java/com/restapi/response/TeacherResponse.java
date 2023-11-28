package com.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Long teacherUserId;
    private Long addressId;
    private String teacherName;
    private String teacherUsername;
    private String password;
    private String email;
}

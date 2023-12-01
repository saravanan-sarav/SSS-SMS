package com.restapi.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAddClassTeacherResponse {
    private Long teacherUserId;
    private Long teacherSubjectId;
    private String teacherName;
    private Long classId;
    private Long standardId;
}

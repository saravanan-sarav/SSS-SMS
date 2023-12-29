package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomRequest {
    @NotEmpty(message = "ClassRoomId is required")
    private Long classRoomId;
    @NotEmpty(message = "StandardId is required")
    private Long standardId;
    @NotEmpty(message = "teacherUserId is required")
    private Long teacherUserId;
    @NotEmpty(message = "tamilTeacherUserId is required")
    private Long tamilTeacherUserId;
    @NotEmpty(message = "englishTeacherUserId is required")
    private Long englishTeacherUserId;
    @NotEmpty(message = "mathsTeacherUserId is required")
    private Long mathsTeacherUserId;
    @NotEmpty(message = "scienceTeacherUserId is required")
    private Long scienceTeacherUserId;
    @NotEmpty(message = "socialTeacherUserId is required")
    private Long socialTeacherUserId;
}

package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomRequest {
    private Long classRoomId;
    private Long standardId;
    private Long teacherUserId;
    private Long tamilTeacherUserId;
    private Long englishTeacherUserId;
    private Long mathsTeacherUserId;
    private Long scienceTeacherUserId;
    private Long socialTeacherUserId;
}

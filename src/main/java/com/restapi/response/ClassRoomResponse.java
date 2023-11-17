package com.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomResponse {
    private Long classRoomId;
    private String classStandard;
    private Long teacherUserId;
    private Long tamilTeacherId;
    private Long englishTeacherId;
    private Long mathsTeacherId;
    private Long scienceTeacherId;
    private Long socialTeacherId;
}

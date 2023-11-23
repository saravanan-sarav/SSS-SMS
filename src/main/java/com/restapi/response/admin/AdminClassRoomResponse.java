package com.restapi.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminClassRoomResponse {
    private Long classId;
    private String standard;
    private Long classTeacherId;
    private String classInCharge;
    private Long tamilTeacherId;
    private String tamilTeacherName;
    private Long englishTeacherId;
    private String englishTeacherName;
    private Long mathsTeacherId;
    private String mathsTeacherName;
    private Long scienceTeacherId;
    private String scienceTeacherName;
    private Long socialTeacherId;
    private String socialTeacherName;


}

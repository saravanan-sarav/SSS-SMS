package com.restapi.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassroomStructureResponse {
    private Long classId;
    private String classStandard;
    private Long classInChargeId;
    private String classInChargeName;
    private String classInChargePhone;
    private String classInChargeEmail;

    private Long tamilTeacherId;
    private String tamilTeacherName;
    private String tamilTeacherPhone;
    private String tamilTeacherEmail;

    private Long englishTeacherId;
    private String englishTeacherName;
    private String englishTeacherPhone;
    private String englishTeacherEmail;

    private Long mathsTeacherId;
    private String mathsTeacherName;
    private String mathsTeacherPhone;
    private String mathsTeacherEmail;

    private Long scienceTeacherId;
    private String scienceTeacherName;
    private String scienceTeacherPhone;
    private String scienceTeacherEmail;

    private Long socialTeacherId;
    private String socialTeacherName;
    private String socialTeacherPhone;
    private String socialTeacherEmail;

}

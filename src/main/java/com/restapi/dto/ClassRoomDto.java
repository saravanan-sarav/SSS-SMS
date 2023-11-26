package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.response.ClassRoomResponse;
import com.restapi.response.admin.AdminClassRoomResponse;
import com.restapi.response.classroom.ClassStandardResponse;
import com.restapi.response.teacher.TeacherClassRoomResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClassRoomDto {
    public ClassRoomResponse mapToClassRoomResponse(ClassRoom classRoom) {
        ClassRoomResponse classRoomResponse = new ClassRoomResponse();
        classRoomResponse.setClassRoomId(classRoom.getId());
        classRoomResponse.setClassStandard(classRoom.getClassStandard().getStandard());
        classRoomResponse.setTeacherUserId(classRoom.getTeacherUserClassRoom().getId());
        classRoomResponse.setTamilTeacherId(classRoom.getTamilTeacherUser().getId());
        classRoomResponse.setEnglishTeacherId(classRoom.getEnglishTeacherUser().getId());
        classRoomResponse.setMathsTeacherId(classRoom.getMathsTeacherUser().getId());
        classRoomResponse.setScienceTeacherId(classRoom.getScienceTeacherUser().getId());
        classRoomResponse.setSocialTeacherId(classRoom.getSocialTeacherUser().getId());
        return classRoomResponse;
    }

    public AdminClassRoomResponse mapToResponse(ClassRoom classRoom, Optional<Teacher> classTeacher, Optional<Teacher> tamilTeacher, Optional<Teacher> englishTeacher, Optional<Teacher> mathsTeacher, Optional<Teacher> scienceTeacher, Optional<Teacher> socialTeacher) {
        AdminClassRoomResponse adminClassRoomResponse = new AdminClassRoomResponse();
        adminClassRoomResponse.setClassId(classRoom.getId());
        adminClassRoomResponse.setStandard(classRoom.getClassStandard().getStandard());

        adminClassRoomResponse.setClassTeacherId(classTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setClassInCharge(classTeacher.get().getFirstName());

        adminClassRoomResponse.setTamilTeacherId(tamilTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setTamilTeacherName(tamilTeacher.get().getFirstName());

        adminClassRoomResponse.setEnglishTeacherId(englishTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setEnglishTeacherName(englishTeacher.get().getFirstName());

        adminClassRoomResponse.setMathsTeacherId(mathsTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setMathsTeacherName(mathsTeacher.get().getFirstName());

        adminClassRoomResponse.setScienceTeacherId(scienceTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setScienceTeacherName(scienceTeacher.get().getFirstName());

        adminClassRoomResponse.setSocialTeacherId(socialTeacher.get().getTeacherUser().getId());
        adminClassRoomResponse.setSocialTeacherName(socialTeacher.get().getFirstName());

        return adminClassRoomResponse;
    }

    public TeacherClassRoomResponse mapToTeacherClassRoomResponse(Parent parent, ClassRoom classRoom, Student student) {
        TeacherClassRoomResponse teacherClassRoomResponse = new TeacherClassRoomResponse();
        teacherClassRoomResponse.setStudentId(parent.getStudentUserForParent().getId());
        teacherClassRoomResponse.setStudentName(parent.getStudentUserForParent().getName());
        teacherClassRoomResponse.setClassName(classRoom.getClassStandard().getStandard());
        teacherClassRoomResponse.setParentId(parent.getParentUser().getId());
        teacherClassRoomResponse.setMotherName(parent.getMotherName());
        teacherClassRoomResponse.setFatherName(parent.getFatherName());
        teacherClassRoomResponse.setMotherPhoneNumber(parent.getMotherPhoneNumber());
        teacherClassRoomResponse.setFatherPhoneNumber(parent.getFatherPhoneNumber());
        teacherClassRoomResponse.setDateOfBirth(student.getDateOfBirth());
        teacherClassRoomResponse.setDateOfJoin(student.getDateOfJoin());
        return teacherClassRoomResponse;
    }

    public ClassStandardResponse mapToStandard(ClassStandard classStandards) {
        return new ClassStandardResponse(classStandards.getId(), classStandards.getStandard());
    }
}

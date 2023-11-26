package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.response.admin.AdminAssignmentResponse;
import com.restapi.response.assignment.AssignmentTypeResponse;
import com.restapi.response.teacher.TeacherAssignmentLoaderResponse;
import org.springframework.stereotype.Component;

@Component
public class AssignmentDto {
    public AdminAssignmentResponse mapToAssignmentResponse(Assignment assignment) {
        AdminAssignmentResponse adminAssignmentResponse = new AdminAssignmentResponse();
        adminAssignmentResponse.setAssignmentId(assignment.getId());
        adminAssignmentResponse.setAssignmentName(assignment.getAssignmentType().getType());
//        adminAssignmentResponse.setClassId(assignment.getClassRoom().getId());
        adminAssignmentResponse.setDeadline(assignment.getDeadline());
        adminAssignmentResponse.setCreatedDate(assignment.getCreatedDate());
        adminAssignmentResponse.setDeadline(assignment.getDeadline());
//        adminAssignmentResponse.setClassStandard(assignment.getClassRoom().getClassStandard().getStandard());
//        adminAssignmentResponse.setAssignmentTypeId(assignment.getAssignmentType().getId());
        adminAssignmentResponse.setTotalGrade(assignment.getTotalGrade());
        adminAssignmentResponse.setCreatedDate(assignment.getCreatedDate());

        return adminAssignmentResponse;
    }

    public AdminAssignmentResponse mapToAdminAssignmentResponse(Assignment assignment, Teacher teacher) {
        AdminAssignmentResponse adminAssignmentResponse = new AdminAssignmentResponse();
        adminAssignmentResponse.setAssignmentId(assignment.getId());
        adminAssignmentResponse.setAssignmentName(assignment.getAssignmentType().getType());
        adminAssignmentResponse.setClassName(assignment.getClassRoom().getClassStandard().getStandard());
        adminAssignmentResponse.setSubject(assignment.getSubjectAssignment().getSubject());
        adminAssignmentResponse.setTeacherId(teacher.getTeacherUser().getId());
        adminAssignmentResponse.setTeacherName(teacher.getFirstName());
        adminAssignmentResponse.setTeacherPhoneNumber(teacher.getPhoneNumber());
        adminAssignmentResponse.setTotalGrade(assignment.getTotalGrade());
        adminAssignmentResponse.setMinScore(assignment.getMinScore());
        adminAssignmentResponse.setCreatedDate(assignment.getCreatedDate());
        adminAssignmentResponse.setDeadline(assignment.getDeadline());
        return adminAssignmentResponse;
    }

    public AssignmentTypeResponse mapToAssignmentTypeResponse(AssignmentType assignmentType) {
        AssignmentTypeResponse assignmentTypeResponse = new AssignmentTypeResponse();
        assignmentTypeResponse.setAssignmentId(assignmentType.getId());
        assignmentTypeResponse.setAssignmentType(assignmentType.getType());
        return assignmentTypeResponse;
    }

    public TeacherAssignmentLoaderResponse maptToTeacherAssignmentLoaderResponse(Assignment assignment) {
        TeacherAssignmentLoaderResponse teacherAssignmentLoaderResponse = new TeacherAssignmentLoaderResponse();
        teacherAssignmentLoaderResponse.setAssignmentId(assignment.getId());
        teacherAssignmentLoaderResponse.setAssignmentName(assignment.getAssignmentType().getType());
        teacherAssignmentLoaderResponse.setClassName(assignment.getClassRoom().getClassStandard().getStandard());
        teacherAssignmentLoaderResponse.setTeacherName(assignment.getTeacherUserAssignment().getName());
        teacherAssignmentLoaderResponse.setSubjectName(assignment.getSubjectAssignment().getSubject());
        teacherAssignmentLoaderResponse.setDueDate(assignment.getDeadline());
        teacherAssignmentLoaderResponse.setTotalMark(assignment.getTotalGrade());
        teacherAssignmentLoaderResponse.setMinScore(assignment.getMinScore());

        return teacherAssignmentLoaderResponse;
    }
}

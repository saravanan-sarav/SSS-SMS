package com.restapi.dto;

import com.restapi.model.Assignment;
import com.restapi.model.ClassRoom;
import com.restapi.model.Subject;
import com.restapi.model.Teacher;
import com.restapi.response.admin.AdminAssignmentResponse;
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
}

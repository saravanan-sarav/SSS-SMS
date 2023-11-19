package com.restapi.dto;

import com.restapi.model.Assignment;
import com.restapi.response.AssignmentResponse;
import org.springframework.stereotype.Component;

@Component
public class AssignmentDto {
    public AssignmentResponse mapToAssignmentResponse(Assignment assignment) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setAssignmentId(assignment.getId());
        assignmentResponse.setAssignmentName(assignment.getAssignmentType().getType());
        assignmentResponse.setClassId(assignment.getClassRoom().getId());
        assignmentResponse.setDeadline(assignment.getDeadline());
        assignmentResponse.setCreatedDate(assignment.getCreatedDate());
        assignmentResponse.setDeadline(assignment.getDeadline());
        assignmentResponse.setClassStandard(assignment.getClassRoom().getClassStandard().getStandard());
        assignmentResponse.setAssignmentTypeId(assignment.getAssignmentType().getId());
        assignmentResponse.setTotalGrade(assignment.getTotalGrade());
        assignmentResponse.setCreatedDate(assignment.getCreatedDate());

        return assignmentResponse;
    }
}

package com.restapi.service;

import com.restapi.dto.AssignmentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.admin.AdminAssignmentResponse;
import com.restapi.response.assignment.AssignmentTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentTypeRepository assignmentTypeRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AssignmentDto assignmentDto;
    public AdminAssignmentResponse
    createAssignment(AssignmentRequest assignmentRequest) {
        AssignmentType assignmentType = assignmentTypeRepository.findById(assignmentRequest.getAssignmentTypeId())
                .orElseThrow(()-> new ResourceNotFoundException("ass_type_Id","ass_type_Id",assignmentRequest.getAssignmentTypeId()));

        ClassRoom classRoom = classRoomRepository.findById(assignmentRequest.getClassId())
                .orElseThrow(()-> new ResourceNotFoundException("classId","classId",assignmentRequest.getClassId()));

        Subject subject = subjectRepository.findById(assignmentRequest.getSubjectId()).orElseThrow(()-> new ResourceNotFoundException("subjectId","SubjectId",assignmentRequest.getSubjectId()));

        Optional<AppUser> teacherUser = userRepository.findById(assignmentRequest.getTeacherUserId());
        Assignment assignment = new Assignment();
        if(assignmentRequest.getAssignmentId()!=null){
            assignment.setId(assignmentRequest.getAssignmentId());
        }
        if(teacherUser!=null) {
            assignment.setAssignmentType(assignmentType);
            assignment.setTeacherUserAssignment(teacherUser.get());
            assignment.setDeadline(assignmentRequest.getDeadline());
            assignment.setTotalGrade(assignmentRequest.getTotalGrade());
            assignment.setClassRoom(classRoom);
            assignment.setComments(assignmentRequest.getComments());
            assignment.setSubjectAssignment(subject);
            assignment.setMinScore(assignmentRequest.getMinScore());
        }
        assignment = assignmentRepository.save(assignment);
        AdminAssignmentResponse adminAssignmentResponse = assignmentDto.mapToAssignmentResponse(assignment);

        return adminAssignmentResponse;
    }

    public Assignment getAssignment(Long id) {
     Assignment assignment = assignmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("assignmentId","assignmentId", id));
     return assignment;
    }

    public List<AssignmentTypeResponse> getAssignmentType() {
        List<AssignmentType> assignmentTypes = assignmentTypeRepository.findAll();
        List<AssignmentTypeResponse> assignmentTypeResponseList = new ArrayList<>();
        for(AssignmentType assignmentType: assignmentTypes){
            assignmentTypeResponseList.add(assignmentDto.mapToAssignmentTypeResponse(assignmentType));
        }
return assignmentTypeResponseList;
    }
}

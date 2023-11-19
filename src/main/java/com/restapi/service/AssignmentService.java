package com.restapi.service;

import com.restapi.dto.AssignmentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Assignment;
import com.restapi.model.AssignmentType;
import com.restapi.model.ClassRoom;
import com.restapi.repository.AssignmentRepository;
import com.restapi.repository.AssignmentTypeRepository;
import com.restapi.repository.ClassRoomRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.AssignmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AssignmentDto assignmentDto;
    public AssignmentResponse createAssignment(AssignmentRequest assignmentRequest) {
        AssignmentType assignmentType = assignmentTypeRepository.findById(assignmentRequest.getAssignmentTypeId())
                .orElseThrow(()-> new ResourceNotFoundException("ass_type_Id","ass_type_Id",assignmentRequest.getAssignmentTypeId()));

        ClassRoom classRoom = classRoomRepository.findById(assignmentRequest.getClassId())
                .orElseThrow(()-> new ResourceNotFoundException("classId","classId",assignmentRequest.getClassId()));

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
        }
        assignment = assignmentRepository.save(assignment);
        AssignmentResponse assignmentResponse = assignmentDto.mapToAssignmentResponse(assignment);

        return  assignmentResponse;
    }

    public Assignment getAssignment(Long id) {
     Assignment assignment = assignmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("assignmentId","assignmentId", id));
     return assignment;
    }
}

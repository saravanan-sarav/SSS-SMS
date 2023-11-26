package com.restapi.service;

import com.restapi.dto.AssignmentDto;
import com.restapi.dto.ClassRoomDto;
import com.restapi.dto.TeacherDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.response.teacher.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private TeacherDto teacherDto;

    @Autowired
    private ClassRoomDto classRoomDto;
    @Autowired
    private AssignmentDto assignmentDto;

    public TeacherProfileResponse findById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findByUserId(id);
        return teacherDto.mapToTeacherProfileResponse(teacher.get());
    }

    public List<TeacherClassRoomResponse> getMyclass(Long teacherUserId) {
        AppUser teacherUser = userRepository.findById(teacherUserId).orElseThrow(() -> new ResourceNotFoundException("userId", "userId", teacherUserId));
        List<TeacherClassRoomResponse> teacherClassRoomResponseList = new ArrayList<>();
        if (teacherUser != null) {
            Optional<ClassRoom> classRoom = classRoomRepository.findByTeacherUserClassRoom(teacherUser.getId());
            if (classRoom.isPresent()) {
                System.out.println(classRoom.get().getClassStandard().getStandard());
                Optional<List<Student>> students = studentRepository.findByClassRoom(classRoom.get().getId());
                for (Student student : students.get()) {
                    System.out.println(student.getFirstName());
                    Parent parent = parentRepository.findByStudentUserId(student.getStudentUser().getId());
                    teacherClassRoomResponseList.add(classRoomDto.mapToTeacherClassRoomResponse(parent, classRoom.get(), student));
                }
            }
        }
        return teacherClassRoomResponseList;
    }

    public List<TeacherAssignmentResponse> findAssignmentByTeacherId(Long id) {
        Optional<List<Assignment>> assignments = assignmentRepository.findByTeacherUserAssignment(id);
        List<TeacherAssignmentResponse> teacherAssignmentResponseList = new ArrayList<>();
        if (assignments.isPresent()) {
            for (Assignment assignment : assignments.get()) {
                teacherAssignmentResponseList.add(teacherDto.MapToTeacherAssignmentResponse(assignment));
            }
        }
        return teacherAssignmentResponseList;
    }

    public TeacherAssignmentLoaderResponse getAssignmentById(Long assignmentId) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if (optionalAssignment.isPresent()) {
            TeacherAssignmentLoaderResponse teacherAssignmentLoaderResponse = assignmentDto.maptToTeacherAssignmentLoaderResponse(optionalAssignment.get());
            return teacherAssignmentLoaderResponse;
        }
        return null;
    }

    public List<TeacherStudentListForAssignment> getStudentListBasedOnAssignment(Long assignmentId) {

        return null;
    }
}

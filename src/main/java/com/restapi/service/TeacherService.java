package com.restapi.service;

import com.restapi.dto.AssignmentDto;
import com.restapi.dto.ClassRoomDto;
import com.restapi.dto.TeacherDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.assignment.TeacherAssignmentMarkEntry;
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
    private AssignmentGradeRepository assignmentGradeRepository;

    @Autowired
    private TeacherDto teacherDto;

    @Autowired
    private ClassRoomDto classRoomDto;
    @Autowired
    private AssignmentDto assignmentDto;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;
    @Autowired
    private LeaveStatusRepository leaveStatusRepository;

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

    public List<TeacherStudentListForAssignmentResponse> getStudentListBasedOnAssignment(Long assignmentId) {
        List<TeacherStudentListForAssignmentResponse> teacherStudentListForAssignmentListResponse = new ArrayList<>();

        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if (optionalAssignment.isPresent()) {
            Optional<List<Student>> optionalStudents = studentRepository.findByClassRoomId(optionalAssignment.get().getClassRoom().getId());
            if (optionalStudents.isPresent()) {
                for (Student student : optionalStudents.get()) {
                    Optional<AssignmentGrade> assignmentGrade = assignmentGradeRepository.findByStudentUserAssignmentGradeAndAssignment(optionalAssignment.get().getId(), student.getStudentUser().getId());
                    if (assignmentGrade.isPresent()) {
                        System.out.println(assignmentGrade.get().getMarksObtained());
                        teacherStudentListForAssignmentListResponse.add(assignmentDto.MapToTeacherStudentListForAssignment(assignmentGrade.get(), student));
                    } else {
                        teacherStudentListForAssignmentListResponse.add(assignmentDto.MapToTeacherStudentListForAssignment(student));
                    }
                }
            }
        }
        return teacherStudentListForAssignmentListResponse;
    }

    public AssignmentGrade studentMarkEntry(TeacherAssignmentMarkEntry teacherAssignmentMarkEntry) {
        Optional<AssignmentGrade> optionalAssignmentGrade = assignmentGradeRepository.findById(teacherAssignmentMarkEntry.getAssignmentGradeId());
        Optional<AppUser> optionalAppUser = userRepository.findById(teacherAssignmentMarkEntry.getStudentUserId());
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(teacherAssignmentMarkEntry.getAssignmentId());
        AssignmentGrade assignmentGrade = new AssignmentGrade();
        if (optionalAssignmentGrade.isPresent()) {
            assignmentGrade.setId(teacherAssignmentMarkEntry.getAssignmentGradeId());
        }
        if (optionalAppUser.isPresent() && optionalAssignment.isPresent()) {
            assignmentGrade.setAssignmentForGrade(optionalAssignment.get());
            assignmentGrade.setMarksObtained(teacherAssignmentMarkEntry.getMarkObtained());
            assignmentGrade.setComments(teacherAssignmentMarkEntry.getComments());
            assignmentGrade.setStudentUserAssignmentGrade(optionalAppUser.get());
            assignmentGrade = assignmentGradeRepository.save(assignmentGrade);

        }

        return assignmentGrade;
    }

    public List<TeacherLeaveDataResponse> findAllLeaveListByTeacherUserId(Long teacherUserId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserId(teacherUserId);
        if (optionalTeacher.isPresent()) {
            List<TeacherLeaveDataResponse> teacherLeaveDataResponseList = new ArrayList<>();
            Optional<ClassRoom> optionalClassRoom = classRoomRepository.findByTeacherUserClassRoom(teacherUserId);
            if (optionalClassRoom.isPresent()) {
                Optional<List<Student>> optionalStudentList = studentRepository.findByClassRoomId(optionalClassRoom.get().getId());
                if (optionalStudentList.isPresent()) {
                    for (Student student : optionalStudentList.get()) {
                        Optional<List<LeaveApplication>> leaveApplicationList = leaveApplicationRepository.findByStudentId(student.getStudentUser().getId());
                        if (leaveApplicationList.isPresent()) {
                            for (LeaveApplication leaveApplication : leaveApplicationList.get()) {
                                teacherLeaveDataResponseList.add(teacherDto.mapToTeacherLeaveDataResponse(leaveApplication, student));
                            }
                        }
                    }
                }
            }
            return teacherLeaveDataResponseList;
        } else {
            return null;
        }
    }

    public Integer teacherLeaveStatusUpdate(TeacherLeaveStatusUpdate teacherLeaveStatusUpdate) {
        Optional<LeaveApplication> optionalLeaveApplication = leaveApplicationRepository.findById(teacherLeaveStatusUpdate.getId());
        Optional<LeaveStatus> optionalLeaveStatus = leaveStatusRepository.findById(teacherLeaveStatusUpdate.getStatusId());
        if(optionalLeaveApplication.isPresent()&& optionalLeaveStatus.isPresent()){
            LeaveApplication leaveApplication = optionalLeaveApplication.get();
            leaveApplication.setLeaveStatus(optionalLeaveStatus.get());
            leaveApplication = leaveApplicationRepository.save(leaveApplication);
            return 1;
        }
        else {
            return 0;
        }
    }
}

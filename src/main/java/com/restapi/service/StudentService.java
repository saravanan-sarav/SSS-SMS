package com.restapi.service;

import com.restapi.dto.StudentDto;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.response.student.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private StudentDto studentDto;
    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;
    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private AssignmentGradeRepository assignmentGradeRepository;

    public StudentProfileResponse getStudentProfile(Long userId) {
        Optional<Student> student = studentRepository.findByUserIdForApprove(userId);
        StudentProfileResponse studentProfileResponse = new StudentProfileResponse();
        if (student.isPresent()) {
            Parent parent = parentRepository.findByStudentUserId(student.get().getStudentUser().getId());
            Student studentDetail = student.get();
            studentProfileResponse.setStudentId(studentDetail.getStudentUser().getId());
            studentProfileResponse.setStudentFirstName(studentDetail.getFirstName());
            studentProfileResponse.setStudentLastName(studentDetail.getLastname());
            studentProfileResponse.setClassStandard(studentDetail.getClassRoom().getClassStandard().getStandard());
            studentProfileResponse.setGender(studentDetail.getGender());
            studentProfileResponse.setStudentStatus(studentDetail.getStudentStatus().getStatus());
            studentProfileResponse.setDateOfBirth(studentDetail.getDateOfBirth());
            studentProfileResponse.setDateOfJoin(studentDetail.getDateOfJoin());
            studentProfileResponse.setAddressId(parent.getAddress().getId());
            studentProfileResponse.setDoorNum(parent.getAddress().getDoorNum());
            studentProfileResponse.setStreet(parent.getAddress().getStreet());
            studentProfileResponse.setAddrLine(parent.getAddress().getAddrLine());
            studentProfileResponse.setCity(parent.getAddress().getCity());
            studentProfileResponse.setState(parent.getAddress().getState());
            studentProfileResponse.setPincode(parent.getAddress().getPincode());

        }
        return studentProfileResponse;
    }

    public StudentAttendanceResponse getAttendance(Long userId) {
        Optional<List<AttendanceRegister>> attendanceRegisters = attendanceRegisterRepository.findAllByUserId(userId);
        if (attendanceRegisters.isPresent()) {
            return studentDto.mapToStudentAttendanceResponse(attendanceRegisters.get(), userId);
        } else {
            return null;
        }
    }

    public List<StudentStatusResponse> getStudentStatus() {
        List<StudentStatus> studentStatusList = studentStatusRepository.findAll();
        List<StudentStatusResponse> studentStatusResponseList = new ArrayList<>();
        for (StudentStatus studentStatus : studentStatusList) {
            studentStatusResponseList.add(new StudentStatusResponse(studentStatus.getId(), studentStatus.getStatus()));
        }
        return studentStatusResponseList;
    }

    public StudentClassroomStructureResponse getTeacherDetails(Long id) {
        Optional<Student> optionalStudent = studentRepository.findByUserId(id);
        if (optionalStudent.isPresent()) {
            Optional<ClassRoom> optionalClassRoom = classRoomRepository.findById(optionalStudent.get().getClassRoom().getId());
            if (optionalClassRoom.isPresent()) {
                Optional<Teacher> optionalClassTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getTeacherUserClassRoom().getId());
                Optional<Teacher> optionalTamilTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getTamilTeacherUser().getId());
                Optional<Teacher> optionalEnglishTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getEnglishTeacherUser().getId());
                Optional<Teacher> optionalMathsTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getMathsTeacherUser().getId());
                Optional<Teacher> optionalScienceTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getScienceTeacherUser().getId());
                Optional<Teacher> optionalSocialTeacher = teacherRepository.findByUserId(optionalClassRoom.get().getSocialTeacherUser().getId());
                return studentDto.mapToClassRoomStructureResponse(optionalClassRoom.get(), optionalClassTeacher.get(), optionalTamilTeacher.get(), optionalEnglishTeacher.get(), optionalMathsTeacher.get(), optionalScienceTeacher.get(), optionalSocialTeacher.get());

            }
        }
        return null;
    }

    public StudentParentProfileResponse getStudentParentProfile(Long userId) {
        StudentParentProfileResponse studentParentProfileResponse = new StudentParentProfileResponse();
        Parent parent = parentRepository.findByStudentUserId(userId);
        studentParentProfileResponse.setParentId(parent.getParentUser().getId());
        studentParentProfileResponse.setFatherName(parent.getFatherName());
        studentParentProfileResponse.setFatherOccupation(parent.getFatherOccupation());
        studentParentProfileResponse.setFatherPhoneNumber(parent.getFatherPhoneNumber());
        studentParentProfileResponse.setMotherName(parent.getMotherName());
        studentParentProfileResponse.setMotherOccupation(parent.getMotherOccupation());
        studentParentProfileResponse.setMotherPhoneNumber(parent.getMotherPhoneNumber());
        studentParentProfileResponse.setEmail(parent.getEmail());
        studentParentProfileResponse.setStreet(parent.getAddress().getStreet());
        studentParentProfileResponse.setDoorNum(parent.getAddress().getDoorNum());
        studentParentProfileResponse.setAddrLine(parent.getAddress().getAddrLine());
        studentParentProfileResponse.setCity(parent.getAddress().getCity());
        studentParentProfileResponse.setState(parent.getAddress().getState());
        studentParentProfileResponse.setPincode(parent.getAddress().getPincode());
        return studentParentProfileResponse;
    }

    public List<StudentAssignmentResponse> getAssignmentDetails(Long id) {
        List<StudentAssignmentResponse> studentAssignmentResponseList = new ArrayList<>();
        Optional<Student> student = studentRepository.findByUserIdForApprove(id);
        if(student.isPresent()){
            Optional<ClassRoom> optionalClassRoom = classRoomRepository.findById(student.get().getClassRoom().getId());
            if(optionalClassRoom.isPresent()){
                Optional<List<Assignment>> optionalAssignment = assignmentRepository.findByClassRoomId(optionalClassRoom.get().getId());
                if(optionalAssignment.isPresent()){
                    for(Assignment assignment:optionalAssignment.get()){
                        Optional<AssignmentGrade> optionalAssignmentGrade = assignmentGradeRepository.findByStudentUserAssignmentGradeAndAssignment(assignment.getId(), student.get().getStudentUser().getId());
                        if(optionalAssignmentGrade.isPresent()){
                            studentAssignmentResponseList.add(studentDto.mapToStudentAssignmentResponse(assignment,optionalAssignmentGrade.get()));
                        }
                        else {
                            studentAssignmentResponseList.add(studentDto.mapToStudentAssignmentResponse(assignment));

                        }
                    }
                }
            }
        }
        return studentAssignmentResponseList;
    }
}

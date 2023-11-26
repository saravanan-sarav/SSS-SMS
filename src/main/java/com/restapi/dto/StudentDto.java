package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.repository.StudentRepository;
import com.restapi.request.ParentRequest;
import com.restapi.response.admin.AdminStudentResponse;
import com.restapi.response.admin.StudentApproveResponse;
import com.restapi.response.student.StudentAttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDto {

    @Autowired
    private StudentRepository studentRepository;
    public Student setStudentDetails(ClassRoom classRoom, StudentStatus studentStatus, AppUser studentAppUser, ParentRequest parentRequest) {
        Student student = new Student();
        if(parentRequest.getStudentUserId()!=null){
            Optional<Student> studentFetch = studentRepository.findByUserId(parentRequest.getStudentUserId());
            if(studentFetch.get().getId()!=null){
                student.setId(studentFetch.get().getId());
            }
        }
        student.setFirstName(parentRequest.getFirstName());
        student.setLastname(parentRequest.getLastName());
        student.setDateOfBirth(parentRequest.getDateOfBirth());
        student.setGender(parentRequest.getGender());
        student.setStudentStatus(studentStatus);
        student.setStudentUser(studentAppUser);
        student.setClassRoom(classRoom);
//        System.out.println("studentCrossed");
        return student;
    }

    public Student MapToApproveForStudent(Student student, StudentStatus studentStatus, ClassRoom classRoom) {
        Student updatedStudent = student;
        updatedStudent.setStudentStatus(studentStatus);
        updatedStudent.setClassRoom(classRoom);
        return updatedStudent;
    }

    public StudentApproveResponse mapToApproveResponse(Student student) {
        StudentApproveResponse studentApproveResponse = new StudentApproveResponse();
        studentApproveResponse.setStudentUserId(student.getStudentUser().getId());
        studentApproveResponse.setStudentName(student.getFirstName());
        studentApproveResponse.setStudentUsername(student.getStudentUser().getUsername());
        studentApproveResponse.setStudentClassId(student.getClassRoom().getId());
        studentApproveResponse.setStudentStatusId(student.getStudentStatus().getId());
        return studentApproveResponse;
    }

    public AdminStudentResponse mapToResponse(Student student, Parent parent) {
        AdminStudentResponse adminStudentResponse = new AdminStudentResponse();
        adminStudentResponse.setId(student.getId());
        adminStudentResponse.setStudentUserId(student.getStudentUser().getId());
        adminStudentResponse.setFirstName(student.getFirstName());
        adminStudentResponse.setLastName(student.getLastname());
        adminStudentResponse.setClassName(student.getClassRoom().getClassStandard().getStandard());
        adminStudentResponse.setGender(student.getGender());
        adminStudentResponse.setFatherName(parent.getFatherName());
        adminStudentResponse.setMotherName(parent.getMotherName());
        adminStudentResponse.setPhoneNumber(parent.getFatherPhoneNumber());
        adminStudentResponse.setStudentStatusId(student.getStudentStatus().getId());
        adminStudentResponse.setDateOfJoin(student.getDateOfJoin());
        return adminStudentResponse;
    }

    public StudentAttendanceResponse mapToStudentAttendanceResponse(List<AttendanceRegister> attendanceRegister, Long userId) {
        StudentAttendanceResponse studentAttendanceResponse = new StudentAttendanceResponse();
        Optional<Student> student = studentRepository.findByUserId(userId);
        if(student.isPresent()){
            studentAttendanceResponse.setJoinedDate(student.get().getDateOfJoin());
            List<LocalDate> attendance = new ArrayList<>();
            for (AttendanceRegister attendanceRegisters  :attendanceRegister){
                attendance.add(attendanceRegisters.getDate());
            }
            studentAttendanceResponse.setDate(attendance);
            return studentAttendanceResponse;
        }
        return null;
    }
}

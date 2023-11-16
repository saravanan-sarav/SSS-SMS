package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.ClassRoom;
import com.restapi.model.Student;
import com.restapi.model.StudentStatus;
import com.restapi.repository.StudentRepository;
import com.restapi.request.ParentRequest;
import com.restapi.response.admin.StudentApproveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDto {

    @Autowired
    private StudentRepository studentRepository;
    public Student setStudent(StudentStatus studentStatus, AppUser studentAppUser, ParentRequest parentRequest) {
        Student student = new Student();
        Student studentFetch = studentRepository.findByUserId(studentAppUser.getId());
        if(studentFetch.getId()!=null){
            student.setId(studentFetch.getId());
        }
        student.setFirstName(parentRequest.getFirstName());
        student.setLastname(parentRequest.getLastname());
        student.setDateOfBirth(parentRequest.getDateOfBirth());
        student.setGender(parentRequest.getGender());
        student.setStudentStatus(studentStatus);
        student.setAppUser(studentAppUser);
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
        studentApproveResponse.setStudentUserId(student.getAppUser().getId());
        studentApproveResponse.setStudentName(student.getFirstName());
        studentApproveResponse.setStudentUsername(student.getAppUser().getUsername());
        studentApproveResponse.setStudentClassId(student.getClassRoom().getId());
        studentApproveResponse.setStudentStatusId(student.getStudentStatus().getId());
        return studentApproveResponse;
    }
}

package com.restapi.service;

import com.restapi.dto.StudentDto;
import com.restapi.model.AttendanceRegister;
import com.restapi.model.Student;
import com.restapi.model.StudentStatus;
import com.restapi.repository.AttendanceRegisterRepository;
import com.restapi.repository.StudentRepository;
import com.restapi.repository.StudentStatusRepository;
import com.restapi.response.student.StudentAttendanceResponse;
import com.restapi.response.student.StudentStatusResponse;
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
    private StudentStatusRepository studentStatusRepository;
    @Autowired
    private StudentDto studentDto;
    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;
    public Student getDetails(Long userId) {
        Optional<Student> student = studentRepository.findByUserIdForApprove(userId);
        return student.get();
    }

    public StudentAttendanceResponse getAttendance(Long userId) {
        Optional<List<AttendanceRegister>> attendanceRegisters = attendanceRegisterRepository.findAllByUserId(userId);
        if(attendanceRegisters.isPresent()){
            return  studentDto.mapToStudentAttendanceResponse(attendanceRegisters.get(),userId);
        }else {
            return null;
        }
    }

    public List<StudentStatusResponse> getStudentStatus() {
        List<StudentStatus> studentStatusList = studentStatusRepository.findAll();
        List<StudentStatusResponse> studentStatusResponseList = new ArrayList<>();
        for(StudentStatus studentStatus:studentStatusList){
            studentStatusResponseList.add(new StudentStatusResponse(studentStatus.getId(),studentStatus.getStatus()));
        }
        return studentStatusResponseList;
    }
}

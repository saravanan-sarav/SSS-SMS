package com.restapi.service;

import com.restapi.model.AttendanceRegister;
import com.restapi.model.Student;
import com.restapi.repository.AttendanceRegisterRepository;
import com.restapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;
    public Student getDetails(Long userId) {
        Optional<Student> student = studentRepository.findByUserIdForApprove(userId);
        return student.get();
    }

    public List<AttendanceRegister> getAttendance(Long userId) {
        Optional<List<AttendanceRegister>> attendanceRegisters = attendanceRegisterRepository.findAllByUserId(userId);

        return attendanceRegisters.get();
    }
}

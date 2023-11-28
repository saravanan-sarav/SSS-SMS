package com.restapi.controller;

import com.restapi.model.AttendanceRegister;
import com.restapi.model.Student;
import com.restapi.request.ParentRequest;
import com.restapi.response.ParentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.response.student.StudentAttendanceResponse;
import com.restapi.response.student.StudentStatusResponse;
import com.restapi.service.StudentService;
import org.aspectj.apache.bcel.classfile.LineNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private StudentService studentService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> create(@PathVariable Long userId){
        Student student = studentService.getDetails(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(student);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/attendance/{userId}")
    public ResponseEntity<APIResponse> getAttendance(@PathVariable Long userId){
        StudentAttendanceResponse attendanceRegisters = studentService.getAttendance(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(attendanceRegisters);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

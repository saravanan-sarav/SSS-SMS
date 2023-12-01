package com.restapi.controller;

import com.restapi.model.Student;
import com.restapi.response.common.APIResponse;
import com.restapi.response.student.*;
import com.restapi.service.StudentService;
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
    public ResponseEntity<APIResponse> getStudentProfile(@PathVariable Long userId){
        StudentProfileResponse studentProfileResponse = studentService.getStudentProfile(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentProfileResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/parent/{userId}")
    public ResponseEntity<APIResponse> getStudentParentProfile(@PathVariable Long userId){
        StudentParentProfileResponse studentParentProfileResponse = studentService.getStudentParentProfile(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentParentProfileResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/attendance/{userId}")
    public ResponseEntity<APIResponse> getAttendance(@PathVariable Long userId){
        StudentAttendanceResponse attendanceRegisters = studentService.getAttendance(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(attendanceRegisters);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    get student class details based on student Id
    @GetMapping("/teacher/details/{id}")
    public ResponseEntity<APIResponse> getTeacherDetails(@PathVariable Long id){
        StudentClassroomStructureResponse studentClassroomStructureResponse = studentService.getTeacherDetails(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentClassroomStructureResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignment/{userId}")
    public ResponseEntity<APIResponse> getAssignmentDetails(@PathVariable Long userId){
        List<StudentAssignmentResponse> studentAssignmentResponse = studentService.getAssignmentDetails(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentAssignmentResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

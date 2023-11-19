package com.restapi.controller;

import com.restapi.model.*;
import com.restapi.request.TeacherRequest;
import com.restapi.request.admin.StudentApproveRequest;
import com.restapi.response.TeacherResponse;
import com.restapi.response.admin.StudentApproveResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AdminService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AdminService adminService;




    @GetMapping("/students/all")
    public ResponseEntity<APIResponse> getAllStudents(){
        List<Student> students = adminService.getAllStudents();

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(students);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/teachers/all")
    public ResponseEntity<APIResponse> getAllTeachers(){
        List<Teacher> teachers = adminService.getAllTeachers();

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(teachers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/classroom/all")
    public ResponseEntity<APIResponse> getAllClassRoom(){
        List<ClassRoom> classRooms = adminService.getAllClassRooms();

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(classRooms);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/parents/all")
    public ResponseEntity<APIResponse> getAllParents(){
        List<Parent> parents = adminService.getAllParents();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(parents);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignments/all")
    public ResponseEntity<APIResponse> getAllAssignments(){
        List<Assignment> assignments = adminService.getAllAssignments();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(assignments);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/teacher")
    public ResponseEntity<APIResponse> addTeacher(@RequestBody TeacherRequest teacherRequest){
        TeacherResponse teacherResponse = adminService.createTeacher(teacherRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(teacherResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/studentupdate")
    public ResponseEntity<APIResponse> approveStudent(@RequestBody StudentApproveRequest studentApproveRequest){
        StudentApproveResponse studentApproveResponse = adminService.approveStudent(studentApproveRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentApproveResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<APIResponse> pendingStudents(){
        List<Student> students = adminService.getPendingApprovals();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(students);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}

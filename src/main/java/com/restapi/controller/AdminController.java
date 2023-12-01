package com.restapi.controller;

import com.restapi.model.*;
import com.restapi.request.TeacherRequest;
import com.restapi.request.admin.DummyTeacherRequest;
import com.restapi.request.admin.StudentApproveRequest;
import com.restapi.response.admin.*;
import com.restapi.response.TeacherResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.response.student.StudentStatusResponse;
import com.restapi.service.AdminService;
import com.restapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/all")
    public ResponseEntity<APIResponse> getAllStudents(){
        List<AdminStudentResponse> students = adminService.getAllStudents();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(students);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/teachers/all")
    public ResponseEntity<APIResponse> getAllTeachers(){
        List<AdminTeacherResponse> teachers = adminService.getAllTeachers();

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(teachers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/classroom/all")
    public ResponseEntity<APIResponse> getAllClassRoom(){
        List<AdminClassRoomResponse> classRooms = adminService.getAllClassRooms();

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(classRooms);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/parents/all")
    public ResponseEntity<APIResponse> getAllParents(){
        List<AdminParentResponse> parents = adminService.getAllParents();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(parents);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignments/all")
    public ResponseEntity<APIResponse> getAllAssignments(){
        List<AdminAssignmentResponse> assignments = adminService.getAllAssignments();
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

    @GetMapping("/status")
    public ResponseEntity<APIResponse> getStudentStatus(){
        List<StudentStatusResponse> studentStatusResponse = studentService.getStudentStatus();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentStatusResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/standard/all")
    public ResponseEntity<APIResponse> getStandardAll(){
        List<AdminStandardResponse> adminStandardResponseList = adminService.getStandardAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(adminStandardResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/add/classroom/teacher/all")
    public ResponseEntity<APIResponse> getTeacherListForAddClassroom(){
        List<AdminAddClassTeacherResponse> adminAddClassTeacherResponseList = adminService.getTeacherListForAddClassroom();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(adminAddClassTeacherResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/attendance/students/{classId}")
    public ResponseEntity<APIResponse> getStudentListForAttendance(@PathVariable Long classId){
        List<AdminStudentListForAttendanceResponse> adminStudentListForAttendanceResponseList = adminService.getStudentListForAttendance(classId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(adminStudentListForAttendanceResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

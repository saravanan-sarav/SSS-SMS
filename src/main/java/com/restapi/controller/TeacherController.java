package com.restapi.controller;

import com.restapi.model.AssignmentGrade;
import com.restapi.request.assignment.TeacherAssignmentMarkEntry;
import com.restapi.response.common.APIResponse;
import com.restapi.response.teacher.*;
import com.restapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private TeacherService teacherService;
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getDetails(@PathVariable Long id){
        TeacherProfileResponse teacher = teacherService.findById(id);
        apiResponse.setData(teacher);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//   Get all assignment belong to the particular teacher
    @GetMapping("/assignment/{teacherUserId}")
    public ResponseEntity<APIResponse> getAssignments(@PathVariable Long teacherUserId){
        List<TeacherAssignmentResponse> assignments = teacherService.findAssignmentByTeacherId(teacherUserId);
        apiResponse.setData(assignments);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    List of student list for particular class which is for student
    @GetMapping("/myclass/{id}")
    public ResponseEntity<APIResponse> getMyClass(@PathVariable Long id){
        List<TeacherClassRoomResponse> students = teacherService.getMyclass(id);
        apiResponse.setData(students);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignment/loader/{assignmentId}")
    public ResponseEntity<APIResponse> getAssignmentByAssignmentId(@PathVariable Long assignmentId){
        TeacherAssignmentLoaderResponse teacherAssignmentLoaderResponse = teacherService.getAssignmentById(assignmentId);
        apiResponse.setData(teacherAssignmentLoaderResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignment/student/{assignmentId}")
    public ResponseEntity<APIResponse> getStudentListBasedOnAssignment(@PathVariable Long assignmentId){
        List<TeacherStudentListForAssignmentResponse> teacherStudentListForAssignmentListResponse = teacherService.getStudentListBasedOnAssignment(assignmentId);
        apiResponse.setData(teacherStudentListForAssignmentListResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/assignment/markentry")
    public ResponseEntity<APIResponse> getStudentListBasedOnAssignment(@RequestBody TeacherAssignmentMarkEntry teacherAssignmentMarkEntry){
        AssignmentGrade assignmentGrade = teacherService.studentMarkEntry(teacherAssignmentMarkEntry);
        apiResponse.setData(assignmentGrade);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/leave/all/{teacherUserId}")
    public ResponseEntity<APIResponse> getLeaveListForTeacher(@PathVariable Long teacherUserId){
        List<TeacherLeaveDataResponse> teacherLeaveDataResponseList  = teacherService.findAllLeaveListByTeacherUserId(teacherUserId);
        apiResponse.setData(teacherLeaveDataResponseList);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/leave/status")
    public ResponseEntity<APIResponse> teacherLeaveStatusUpdate(@RequestBody TeacherLeaveStatusUpdate teacherLeaveStatusUpdate){
        Integer value  = teacherService.teacherLeaveStatusUpdate(teacherLeaveStatusUpdate);
        apiResponse.setData(value);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

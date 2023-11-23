package com.restapi.controller;

import com.restapi.model.Assignment;
import com.restapi.model.Student;
import com.restapi.model.Teacher;
import com.restapi.response.common.APIResponse;
import com.restapi.response.teacher.TeacherAssignmentResponse;
import com.restapi.response.teacher.TeacherClassRoomResponse;
import com.restapi.response.teacher.TeacherProfileResponse;
import com.restapi.service.AssignmentService;
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

    @GetMapping("/assignment/{id}")
    public ResponseEntity<APIResponse> getAssignments(@PathVariable Long id){
        List<TeacherAssignmentResponse> assignments = teacherService.findAssignmentByTeacherId(id);
        apiResponse.setData(assignments);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/myclass/{id}")
    public ResponseEntity<APIResponse> getMyClass(@PathVariable Long id){
        List<TeacherClassRoomResponse> students = teacherService.getMyclass(id);
        apiResponse.setData(students);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}

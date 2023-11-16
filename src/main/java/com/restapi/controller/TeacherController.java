package com.restapi.controller;

import com.restapi.model.Teacher;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.AssignmentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private TeacherService teacherService;
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getDetails(@PathVariable Long id){
        Teacher teacher = teacherService.findById(id);
        apiResponse.setData(teacher);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}

package com.restapi.controller;

import com.restapi.model.Assignment;
import com.restapi.model.Role;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.AssignmentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/assignment")
@RolesAllowed({Role.TEACHER, Role.ADMIN})
public class AssignmentController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getAssignment(@PathVariable Long id){
        Assignment assignment = assignmentService.getAssignment(id);
        apiResponse.setData(assignment);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> postAssignment(@RequestBody AssignmentRequest assignmentRequest){
        AssignmentResponse assignmentResponse = assignmentService.createAssignment(assignmentRequest);
        apiResponse.setData(assignmentResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}

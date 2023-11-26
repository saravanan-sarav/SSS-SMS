package com.restapi.controller;

import com.restapi.model.Assignment;
import com.restapi.model.Role;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.admin.AdminAssignmentResponse;
import com.restapi.response.assignment.AssignmentTypeResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/assignment")

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
        AdminAssignmentResponse adminAssignmentResponse = assignmentService.createAssignment(assignmentRequest);
        apiResponse.setData(adminAssignmentResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/assignmentType")
    public ResponseEntity<APIResponse> postAssignment(){
        List<AssignmentTypeResponse> assignmentTypeResponse = assignmentService.getAssignmentType();
        apiResponse.setData(assignmentTypeResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}

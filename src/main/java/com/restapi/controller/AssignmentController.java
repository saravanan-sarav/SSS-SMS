package com.restapi.controller;

import com.restapi.request.AssignmentRequest;
import com.restapi.response.AssignmentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AssignmentService assignmentService;
    @PostMapping
    public ResponseEntity<APIResponse> postAssignment(@RequestBody AssignmentRequest assignmentRequest){
        AssignmentResponse assignmentResponse = assignmentService.createAssignment(assignmentRequest);
        apiResponse.setData(assignmentResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

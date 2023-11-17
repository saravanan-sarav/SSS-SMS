package com.restapi.controller;

import com.restapi.dto.ParentDto;
import com.restapi.dto.StudentDto;
import com.restapi.model.AppUser;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import com.restapi.request.ParentRequest;
import com.restapi.response.ParentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ParentService parentService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getDetails(@PathVariable Long id){
        Parent parent = parentService.findById(id);
        apiResponse.setData(parent);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> create(@RequestBody ParentRequest parentRequest){
        ParentResponse parentResponse = parentService.create(parentRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(parentResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

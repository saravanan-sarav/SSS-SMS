package com.restapi.controller;

import com.restapi.response.common.APIResponse;
import com.restapi.response.subject.SubjectResponse;
import com.restapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getSubjectAll() {
        List<SubjectResponse> subjectResponse = subjectService.findAllSubjects();
        apiResponse.setData(subjectResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<APIResponse> getTeacherCount() {
        Object teacherCountResponseList = subjectService.getTeacherCount();
        apiResponse.setData(teacherCountResponseList);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}

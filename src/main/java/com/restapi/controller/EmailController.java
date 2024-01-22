package com.restapi.controller;

import com.restapi.request.LoginAttemptRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private APIResponse apiResponse;
    @PostMapping("/login/alert")
    public ResponseEntity<APIResponse> loginAlert(@RequestBody LoginAttemptRequest loginAttemptRequest) {
        String response = emailService.loginAttemptMail(loginAttemptRequest);
        apiResponse.setData(response);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

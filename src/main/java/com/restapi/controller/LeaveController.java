package com.restapi.controller;

import com.restapi.model.Parent;
import com.restapi.model.Role;
import com.restapi.request.TeacherRequest;
import com.restapi.request.leave.LeaveApplyRequest;
import com.restapi.response.TeacherResponse;
import com.restapi.response.admin.AdminAssignmentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.response.leave.LeaveApplyResponse;
import com.restapi.response.leave.LeaveReasonResponse;
import com.restapi.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ParentService parentService;
    @GetMapping("/{studentUserId}")
    public ResponseEntity<APIResponse> getStudentLeaveList(@PathVariable long studentUserId){
        List<AdminAssignmentResponse> leaveApplyResponses = parentService.getStudentLeaveList(studentUserId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(leaveApplyResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/apply")
    public ResponseEntity<APIResponse> applyLeave(@RequestBody LeaveApplyRequest leaveApplyRequest){
        System.out.println("ApplyCame");
        LeaveApplyResponse leaveApplyResponse = parentService.applyLeave(leaveApplyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(leaveApplyResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/reasons")
    public ResponseEntity<APIResponse> getLeaveReason(){
        List<LeaveReasonResponse> leaveReasonResponseList = parentService.getLeaveReason();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(leaveReasonResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/recent/{StudentUserId}")
    public ResponseEntity<APIResponse> getRecentLeaveApplication(@PathVariable Long StudentUserId){
        LeaveApplyResponse leaveApplyResponse = parentService.getRecentLeaveApplication(StudentUserId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(leaveApplyResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

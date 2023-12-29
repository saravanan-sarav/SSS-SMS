package com.restapi.dto;


import com.restapi.model.LeaveApplication;
import com.restapi.model.LeaveStatus;
import com.restapi.model.Student;
import com.restapi.response.leave.LeaveApplyResponse;
import org.springframework.stereotype.Component;

@Component
public class LeaveDto {
    public LeaveApplyResponse mapToLeaveApplyResponse(LeaveApplication leaveApplication, Student student) {
        LeaveApplyResponse leaveApplyResponse = new LeaveApplyResponse();
        leaveApplyResponse.setStudentFirstName(student.getFirstName());
        leaveApplyResponse.setStudentLastName(student.getLastname());
        leaveApplyResponse.setApplyDate(leaveApplication.getApplyDate());
        leaveApplyResponse.setFromDate(leaveApplication.getFromDate());
        leaveApplyResponse.setToDate(leaveApplication.getToDate());
        leaveApplyResponse.setFromTime(leaveApplication.getFromTime());
        leaveApplyResponse.setToTime(leaveApplication.getToTime());
        leaveApplyResponse.setComments(leaveApplication.getComments());
        leaveApplyResponse.setLeaveStatus(leaveApplication.getLeaveStatus().getLeaveStatus());
        return leaveApplyResponse;
    }
}

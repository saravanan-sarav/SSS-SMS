package com.restapi.response.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplyResponse {
    private String studentFirstName;
    private String studentLastName;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String comments;
    private String leaveStatus;
    private String leaveReason;
    private String leaveType;
    private LocalDateTime applyDate;
}

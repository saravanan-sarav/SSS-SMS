package com.restapi.response.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentLeaveDataResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long leaveTypeId;
    private String leaveType;
    private LocalDate appliedDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String leaveReason;
    private String comments;
    private Long leaveStatusId;
    private String leaveStatus;
}

package com.restapi.request.leave;

import com.restapi.model.LeaveStatus;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplyRequest {
    private Long id;
    private Long leaveTypeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String comments;
    private Long parentUserId;
    private Long studentUserId;
}

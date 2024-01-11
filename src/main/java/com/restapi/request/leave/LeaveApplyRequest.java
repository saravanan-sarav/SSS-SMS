package com.restapi.request.leave;

import com.restapi.model.LeaveStatus;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplyRequest {
    private Long id;
    @NotEmpty
    private Long leaveTypeId;
    @NotEmpty
    private Long leaveReasonId;
    @NotEmpty
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    @NotEmpty
    @Size(min = 2, max = 200, message = "Comment is too Long")
    private String comments;
    @NotEmpty
    private Long parentUserId;
    @NotEmpty
    private Long studentUserId;
}

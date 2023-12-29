package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {
    @NotEmpty(message = "classId is required")
    private Long classId;
    @NotEmpty(message = "StudentId is required")
    private Long studentUserId;
}

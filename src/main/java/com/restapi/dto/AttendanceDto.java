package com.restapi.dto;

import com.restapi.model.AttendanceRegister;
import com.restapi.response.AttendanceResponse;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Component
public class AttendanceDto {
    public AttendanceResponse mapToAttendanceResponse(List<AttendanceRegister> attendanceRegisters) {
        AttendanceResponse attendanceResponse = new AttendanceResponse();
        attendanceResponse.setAttendanceResponses(attendanceRegisters);
        return attendanceResponse;
    }

}

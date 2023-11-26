package com.restapi.response;

import com.restapi.model.AttendanceRegister;
import com.restapi.response.teacher.TeacherStudentAttendanceResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AttendanceResponse extends TeacherStudentAttendanceResponse {
    List<AttendanceRegister> attendanceResponses = new ArrayList<>();
}

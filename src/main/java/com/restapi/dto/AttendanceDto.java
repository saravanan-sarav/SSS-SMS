package com.restapi.dto;

import com.restapi.model.AttendanceRegister;
import com.restapi.model.Student;
import com.restapi.response.AttendanceResponse;
import com.restapi.response.teacher.TeacherStudentAttendanceResponse;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Component
public class AttendanceDto {
    public AttendanceResponse mapToAttendanceResponse(List<AttendanceRegister> attendanceRegisters) {
        AttendanceResponse attendanceResponse = new AttendanceResponse();
        attendanceResponse.setAttendanceResponses(attendanceRegisters);
        return attendanceResponse;
    }

    public TeacherStudentAttendanceResponse mapToStudentListForAttendance(Student student, LocalDate today) {
        return new TeacherStudentAttendanceResponse(student.getStudentUser().getId(), student.getFirstName(),student.getLastname(),student.getClassRoom().getClassStandard().getStandard(),today);

    }
    public TeacherStudentAttendanceResponse mapToStudentListForAttendance(Student student) {
        TeacherStudentAttendanceResponse teacherStudentAttendanceResponse = new TeacherStudentAttendanceResponse();
        teacherStudentAttendanceResponse.setStudentId(student.getStudentUser().getId());
        teacherStudentAttendanceResponse.setStudentFirstName(student.getFirstName());
        teacherStudentAttendanceResponse.setStudentLastName(student.getLastname());
        teacherStudentAttendanceResponse.setClassName(student.getClassRoom().getClassStandard().getStandard());
        return teacherStudentAttendanceResponse;

    }
}

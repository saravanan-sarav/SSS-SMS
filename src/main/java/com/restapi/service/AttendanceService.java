package com.restapi.service;

import com.restapi.dto.AttendanceDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.AttendanceRegister;
import com.restapi.model.ClassRoom;
import com.restapi.model.Student;
import com.restapi.repository.AttendanceRegisterRepository;
import com.restapi.repository.ClassRoomRepository;
import com.restapi.repository.StudentRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AttendanceRequest;
import com.restapi.response.AttendanceResponse;
import com.restapi.response.teacher.TeacherStudentAttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.*;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private AttendanceDto attendanceDto;

    public AttendanceResponse findByStudentId(Long id) {
        List<AttendanceRegister> attendanceRegisterForStudent = attendanceRegisterRepository.findAllByUserId(id).get();
        AttendanceResponse attendanceResponse = attendanceDto.mapToAttendanceResponse(attendanceRegisterForStudent);
        return attendanceResponse;
    }

    public List<TeacherStudentAttendanceResponse> findStudentListByClassId(Long id) {
        Optional<List<Student>> studentListOfClass = studentRepository.findByClassRoom(id);
        System.out.println(studentListOfClass.get());
        List<TeacherStudentAttendanceResponse> teacherStudentAttendanceResponseList = new ArrayList<>();
        if (studentListOfClass.isPresent()) {
            for (Student student : studentListOfClass.get()) {
                System.out.println(student.getFirstName());
                System.out.println(student.getStudentUser().getId());
                Optional<List<AttendanceRegister>> attendanceRegister = attendanceRegisterRepository.findAllUserId(student.getStudentUser().getId());
                if(attendanceRegister.isPresent()){
                    Optional<AttendanceRegister> attendanceRegisterWithDate = attendanceRegisterRepository.findAllByUserIdWithTodayDate(student.getStudentUser().getId());
                    System.out.println(attendanceRegister.get());
                    if (attendanceRegisterWithDate.isPresent()) {
                        System.out.println("If Block");
                        teacherStudentAttendanceResponseList.add(attendanceDto.mapToStudentListForAttendance(student, attendanceRegisterWithDate.get().getDate()));
                    } else {
                        System.out.println("Else Block");
                        teacherStudentAttendanceResponseList.add(attendanceDto.mapToStudentListForAttendance(student));
                    }
                }
            }
        }
        return teacherStudentAttendanceResponseList;
    }

    public AttendanceRegister markAttendanceForStudent(AttendanceRequest attendanceRequest) {
        AppUser studentAppUser = userRepository.findById(attendanceRequest.getStudentUserId())
                .orElseThrow(() -> new ResourceNotFoundException("studentUserId", "studentUserId", attendanceRequest.getStudentUserId()));

        ClassRoom classRoom = classRoomRepository.findById(attendanceRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("classId", "classId", attendanceRequest.getClassId()));

        AttendanceRegister attendanceRegister = new AttendanceRegister();
        attendanceRegister.setStudentUserAttendance(studentAppUser);
        attendanceRegister.setClassRoom(classRoom);

        attendanceRegister = attendanceRegisterRepository.save(attendanceRegister);
        return attendanceRegister;
    }
}

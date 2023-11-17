package com.restapi.service;

import com.restapi.dto.AttendanceDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.AttendanceRegister;
import com.restapi.model.ClassRoom;
import com.restapi.repository.AttendanceRegisterRepository;
import com.restapi.repository.ClassRoomRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AttendanceRequest;
import com.restapi.response.AttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private AttendanceDto attendanceDto;
    public AttendanceResponse findByStudentId(Long id) {
        List<AttendanceRegister> attendanceRegisterForStudent = attendanceRegisterRepository.findAllByUserId(id).get();
        AttendanceResponse attendanceResponse = attendanceDto.mapToAttendanceResponse(attendanceRegisterForStudent);
        return attendanceResponse;
    }

    public AttendanceResponse findByClassId(Long id) {
        List<AttendanceRegister> attendanceRegistersForClass = attendanceRegisterRepository.findAllByClassId(id);
        AttendanceResponse attendanceResponse = attendanceDto.mapToAttendanceResponse(attendanceRegistersForClass);
        return attendanceResponse;
    }

    public AttendanceRegister markAttendanceForStudent(AttendanceRequest attendanceRequest) {
        AppUser studentAppUser = userRepository.findById(attendanceRequest.getStudentUserId())
                .orElseThrow(()-> new ResourceNotFoundException("studentUserId","studentUserId", attendanceRequest.getStudentUserId()));

        ClassRoom classRoom = classRoomRepository.findById(attendanceRequest.getClassId())
                .orElseThrow(()-> new ResourceNotFoundException("classId","classId",attendanceRequest.getClassId()));

        AttendanceRegister attendanceRegister = new AttendanceRegister();
        attendanceRegister.setStudentUserAttendance(studentAppUser);
        attendanceRegister.setClassRoom(classRoom);

        attendanceRegister = attendanceRegisterRepository.save(attendanceRegister);
        return attendanceRegister;
    }
}

package com.restapi.controller;

import com.restapi.model.AttendanceRegister;
import com.restapi.model.ClassRoom;
import com.restapi.request.AttendanceRequest;
import com.restapi.response.AttendanceResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AttendanceService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private APIResponse apiResponse;
    @GetMapping("/student/{userId}")
    public ResponseEntity<APIResponse> getStudentAttendance(@PathVariable Long userId){
         AttendanceResponse attendanceResponse = attendanceService.findByStudentId(userId);
        apiResponse.setData(attendanceResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<APIResponse> getClassRoomAttendance(@PathVariable Long id){
        AttendanceResponse attendanceResponse = attendanceService.findByClassId(id);
        apiResponse.setData(attendanceResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/entry")
    public ResponseEntity<APIResponse> markAttendance(@RequestBody AttendanceRequest attendanceRequest){
        AttendanceRegister attendanceRegister = attendanceService.markAttendanceForStudent(attendanceRequest);
        apiResponse.setData(attendanceRegister);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }





}

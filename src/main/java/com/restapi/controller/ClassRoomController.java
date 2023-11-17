package com.restapi.controller;

import com.restapi.model.ClassRoom;
import com.restapi.model.Role;
import com.restapi.request.ClassRoomRequest;
import com.restapi.response.ClassRoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/classroom")
@RolesAllowed(Role.ADMIN)
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private APIResponse apiResponse;
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getClassRoom(@PathVariable Long id){
        ClassRoom classRoom = classRoomService.findById(id);
        apiResponse.setData(classRoom);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody ClassRoomRequest classRoomRequest){
        ClassRoomResponse classRoomResponse = classRoomService.createClassRoom(classRoomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(classRoomResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

package com.restapi.service;

import com.restapi.dto.AssignmentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.AssignmentRequest;
import com.restapi.response.AssignmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findById(Long id) {
        Teacher teacher = teacherRepository.findByUserId(id);
        return teacher;
    }



}

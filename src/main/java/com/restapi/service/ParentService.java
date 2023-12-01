package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.dto.AuthDto;
import com.restapi.dto.ParentDto;
import com.restapi.dto.StudentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.ParentRequest;
import com.restapi.response.ParentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ParentDto parentDto;

    @Autowired
    private AuthDto authDto;

    @Autowired
    private StudentDto studentDto;

    @Autowired
    private AddressDto addressDto;

    @Autowired
    private ClassStandardRepository classStandardRepository;


    public Parent findById(Long id) {
        Optional<Parent> parent = parentRepository.findByUserId(id);
        return parent.get();
    }

    public ParentResponse create(ParentRequest parentRequest) {
        Role studentRole = roleRepository.findById(1)
                .orElseThrow(()-> new ResourceNotFoundException("roleId","roleId",1));

        Role parentRole = roleRepository.findById(2)
                .orElseThrow(()-> new ResourceNotFoundException("roleId","roleId",2));

        StudentStatus studentStatus = studentStatusRepository.findById(1l)
                .orElseThrow(()-> new ResourceNotFoundException("statusId","statusId",1));

        ClassRoom classRoom = classRoomRepository.findByStandardId(parentRequest.getClassId())
                .orElseThrow(()-> new ResourceNotFoundException("classId","classId",parentRequest.getClassId()));

        AppUser parentAppUser = userRepository.save(authDto.setParentAuth(parentRole,parentRequest));

        AppUser studentAppUser = userRepository.save(authDto.setStudentAuth(studentRole,parentRequest));

        Address parentAddress = addressRepository.save(addressDto.setParentAddress(parentRequest));

        Student student = studentRepository.save(studentDto.setStudentDetails(classRoom,studentStatus,studentAppUser,parentRequest));

        Parent parent = parentRepository.save(parentDto.setParentDetails(parentAppUser,studentAppUser,parentAddress,parentRequest));

        ParentResponse parentResponse = parentDto.responseConversion(parent);

        return parentResponse;
    }
}

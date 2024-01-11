package com.restapi.service;

import com.restapi.controller.LeaveController;
import com.restapi.dto.*;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.ParentRequest;
import com.restapi.request.leave.LeaveApplyRequest;
import com.restapi.response.ParentResponse;
import com.restapi.response.admin.AdminAssignmentResponse;
import com.restapi.response.leave.LeaveApplyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    private LeaveDto leaveDto;

    @Autowired
    private ClassStandardRepository classStandardRepository;
    @Autowired
    private StorageService storageService;

    @Autowired
    private LeaveStatusRepository leaveStatusRepository;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    public Parent findById(Long id) {
        Optional<Parent> parent = parentRepository.findByUserId(id);
        return parent.get();
    }

    public ParentResponse create(ParentRequest parentRequest) {
        Role studentRole = roleRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("roleId", "roleId", 1));

        Role parentRole = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("roleId", "roleId", 2));

        StudentStatus studentStatus = studentStatusRepository.findById(1l)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", 1));

        ClassRoom classRoom = classRoomRepository.findByStandardId(parentRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("classId", "classId", parentRequest.getClassId()));

        AppUser parentAppUser = userRepository.save(authDto.setParentAuth(parentRole, parentRequest));

        AppUser studentAppUser = userRepository.save(authDto.setStudentAuth(studentRole, parentRequest));

        Address parentAddress = addressRepository.save(addressDto.setParentAddress(parentRequest));

        Student student = studentRepository.save(studentDto.setStudentDetails(classRoom, studentStatus, studentAppUser, parentRequest));

        Parent parent = parentRepository.save(parentDto.setParentDetails(parentAppUser, studentAppUser, parentAddress, parentRequest));

        return parentDto.responseConversion(parent);
    }

    public File getFile(Long id) {
        Optional<Student> student = studentRepository.findByUserId(id);

        Resource resource = storageService.loadFileAsResource(student.get().getPhoto());

        try {
            return resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LeaveApplyResponse applyLeave(LeaveApplyRequest leaveApplyRequest) {
        Optional<Parent> optionalParent = parentRepository.findByUserId(leaveApplyRequest.getParentUserId());
        if (optionalParent.isPresent()) {
            Optional<Student> optionalStudent = studentRepository.findByUserId(leaveApplyRequest.getStudentUserId());
            if (optionalStudent.isPresent()) {
                Optional<LeaveStatus> leaveStatus = leaveStatusRepository.findById(1L);
                Optional<LeaveType> optionalLeaveType = leaveTypeRepository.findById(leaveApplyRequest.getLeaveTypeId());
                if (leaveStatus.isPresent() && optionalLeaveType.isPresent()) {
                    LeaveApplication leaveApplication = new LeaveApplication();
                    leaveApplication.setId(leaveApplyRequest.getId());
                    leaveApplication.setLeaveStatus(leaveStatus.get());
                    leaveApplication.setLeaveApplicationParent(optionalParent.get().getParentUser());
                    leaveApplication.setLeaveApplicationStudent(optionalStudent.get().getStudentUser());
                    leaveApplication.setFromDate(leaveApplyRequest.getFromDate());
                    leaveApplication.setToDate(leaveApplyRequest.getToDate());
                    leaveApplication.setFromTime(leaveApplyRequest.getFromTime());
                    leaveApplication.setToTime(leaveApplyRequest.getToTime());
                    leaveApplication.setComments(leaveApplyRequest.getComments());
                    leaveApplication.setLeaveType(optionalLeaveType.get());
                    leaveApplication = leaveApplicationRepository.save(leaveApplication);
                    return leaveDto.mapToLeaveApplyResponse(leaveApplication, optionalStudent.get());
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<AdminAssignmentResponse> getStudentLeaveList(long studentUserId) {
        return null;
    }
}

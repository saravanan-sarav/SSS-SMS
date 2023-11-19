package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.dto.AuthDto;
import com.restapi.dto.StudentDto;
import com.restapi.dto.TeacherDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.TeacherRequest;
import com.restapi.request.admin.StudentApproveRequest;
import com.restapi.response.TeacherResponse;
import com.restapi.response.admin.StudentApproveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private ClassStandardRepository classStandardRepository;

    @Autowired
    private AuthDto authDto;

    @Autowired
    private AddressDto addressDto;

    @Autowired
    private TeacherDto teacherDto;

    @Autowired
    private StudentDto studentDto;

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    public List<Parent> getAllParents() {
        List<Parent> parents = parentRepository.findAll();
        return parents;
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments;

    }

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
//        userid, addressid, subjectid
        Role teacherRole = roleRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("roleId", "roleId", 3));
        System.out.println(teacherRequest.getDateOfBirth());
        AppUser teacherAppUser = userRepository.save(authDto.setTeacherAuth(teacherRole, teacherRequest));
        Address address = addressRepository.save(addressDto.setTeacherAddress(teacherRequest));

        Subject subject = subjectRepository.findById(teacherRequest.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("subjectId", "subjectId", teacherRequest.getSubjectId()));

        Teacher teacher = teacherRepository.save(teacherDto.mapToTeacher(teacherAppUser, address, subject, teacherRequest));

        TeacherResponse teacherResponse = teacherDto.mapToTeacherResponse(teacher);
        return teacherResponse;
    }

    public StudentApproveResponse approveStudent(StudentApproveRequest studentApproveRequest) {
        System.out.println(studentApproveRequest.getStudentUserId());
        Optional<Student> optionalStudent = studentRepository.findByUserIdForApprove(studentApproveRequest.getStudentUserId());
        Student student = optionalStudent.get();
        ClassStandard classStandard = classStandardRepository.findById(studentApproveRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("stdId", "stdId", 2));

        StudentStatus studentStatus = studentStatusRepository.findById(studentApproveRequest.getStudentStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", studentApproveRequest.getStudentStatusId()));

        ClassRoom classRoom = classRoomRepository.findById(classStandard.getId())
                .orElseThrow(() -> new ResourceNotFoundException("classId", "classId", studentApproveRequest.getClassId()));

        student = studentDto.MapToApproveForStudent(student, studentStatus, classRoom);
        StudentApproveResponse studentApproveResponse = studentDto.mapToApproveResponse(student);
        return studentApproveResponse;
    }


    public List<Student> getPendingApprovals() {
        Optional<List<Student>> optionalStudents = studentRepository.findPendingStudents();
        return optionalStudents.get();
    }

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }
}

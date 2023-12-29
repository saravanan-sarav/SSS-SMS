package com.restapi.service;

import com.restapi.dto.*;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.TeacherRequest;
import com.restapi.request.admin.StudentApproveRequest;
import com.restapi.response.TeacherResponse;
import com.restapi.response.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ClassRoomDto classRoomDto;

    @Autowired
    private StudentDto studentDto;

    @Autowired
    private AssignmentDto assignmentDto;
    @Autowired
    private ParentDto parentDto;

    @Autowired
    private AttendanceRegisterRepository attendanceRegisterRepository;
    public List<AdminStudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<AdminStudentResponse> adminStudentResponseList = new ArrayList<>();
        for (Student student : students) {
            Parent parent = parentRepository.findByStudentUserId(student.getStudentUser().getId());
            adminStudentResponseList.add(studentDto.mapToResponse(student, parent));
        }
        return adminStudentResponseList;
    }

    public List<AdminTeacherResponse> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<AdminTeacherResponse> adminStudentResponseList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            Subject subject = subjectRepository.findById(teacher.getSubject().getId()).get();
            System.out.println("Teacher ID " + teacher.getTeacherUser().getId());
            Optional<ClassRoom> classRoom = classRoomRepository.findByTeacherUserClassRoom(teacher.getTeacherUser().getId());
            System.out.println("ClassRoom Detected");
            if (classRoom.isPresent()) {
                adminStudentResponseList.add(teacherDto.mapToAdminTeacherResponse(teacher, subject, classRoom.get()));
            } else {
                adminStudentResponseList.add(teacherDto.mapToAdminTeacherResponse(teacher, subject));
            }
        }
        return adminStudentResponseList;
    }

    public List<AdminParentResponse> getAllParents() {
        List<Parent> parents = parentRepository.findAll();
        List<AdminParentResponse> adminParentResponseList = new ArrayList<>();
        for (Parent parent : parents){
            Student student = studentRepository.findByUserIdForApprove(parent.getStudentUserForParent().getId()).orElseThrow(()-> new ResourceNotFoundException("StudentId","StudentId",parent.getStudentUserForParent().getId()));
            adminParentResponseList.add(parentDto.mapToAdminParentResponse(parent,student));
        }
        return adminParentResponseList;
    }

    public List<AdminAssignmentResponse> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        List<AdminAssignmentResponse> adminAssignmentResponseList = new ArrayList<>();
        for (Assignment assignment : assignments) {
            Optional<Teacher> optionalTeacher = teacherRepository.findByUserId(assignment.getTeacherUserAssignment().getId());
            adminAssignmentResponseList.add(assignmentDto.mapToAdminAssignmentResponse(assignment, optionalTeacher.get()));

        }
        return adminAssignmentResponseList;
    }

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
//        userid, addressid, subjectid
        Role teacherRole = roleRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("roleId", "roleId", 3));
        AppUser teacherAppUser = userRepository.save(authDto.setTeacherAuth(teacherRole, teacherRequest));
        Address address = addressRepository.save(addressDto.setTeacherAddress(teacherRequest));
        Subject subject = subjectRepository.findById(teacherRequest.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("subjectId", "subjectId", teacherRequest.getSubjectId()));
        System.out.println(subject.getSubject());
        Teacher teacher = teacherRepository.save(teacherDto.mapToTeacher(teacherAppUser, address, subject, teacherRequest));
        System.out.println(teacher.getEmail());
        TeacherResponse teacherResponse = teacherDto.mapToTeacherResponse(teacher);
        return teacherResponse;
    }

    public StudentApproveResponse approveStudent(StudentApproveRequest studentApproveRequest) {
        System.out.println(" Came Here"+studentApproveRequest.getStudentUserId());
        Optional<Student> optionalStudent = studentRepository.findByUserIdForApprove(studentApproveRequest.getStudentUserId());
        Student student = optionalStudent.get();

        StudentStatus studentStatus = studentStatusRepository.findById(studentApproveRequest.getStudentStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", studentApproveRequest.getStudentStatusId()));

        ClassRoom classRoom = classRoomRepository.findById(studentApproveRequest.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("classId", "classId", studentApproveRequest.getClassId()));

        student = studentDto.MapToApproveForStudent(student, studentStatus, classRoom);
        student = studentRepository.save(student);
        StudentApproveResponse studentApproveResponse = studentDto.mapToApproveResponse(student);
        return studentApproveResponse;
    }


    public List<Student> getPendingApprovals() {
        Optional<List<Student>> optionalStudents = studentRepository.findPendingStudents();
        return optionalStudents.get();
    }

    public List<AdminClassRoomResponse> getAllClassRooms() {
        List<ClassRoom> classRoomList = classRoomRepository.findAll();
        List<AdminClassRoomResponse> adminStudentResponseList = new ArrayList<>();
        for (ClassRoom classRoom : classRoomList) {
            Optional<Teacher> classTeacher = teacherRepository.findByUserId(classRoom.getTeacherUserClassRoom().getId());
            Optional<Teacher> tamilTeacher = teacherRepository.findByUserId(classRoom.getTamilTeacherUser().getId());
            Optional<Teacher> englishTeacher = teacherRepository.findByUserId(classRoom.getEnglishTeacherUser().getId());
            Optional<Teacher> mathsTeacher = teacherRepository.findByUserId(classRoom.getMathsTeacherUser().getId());
            Optional<Teacher> scienceTeacher = teacherRepository.findByUserId(classRoom.getScienceTeacherUser().getId());
            Optional<Teacher> socialTeacher = teacherRepository.findByUserId(classRoom.getSocialTeacherUser().getId());
            adminStudentResponseList.add(classRoomDto.mapToResponse
                    (classRoom, classTeacher, tamilTeacher, englishTeacher, mathsTeacher, scienceTeacher, socialTeacher));
        }
        return adminStudentResponseList;
    }

    public List<AdminStandardResponse> getStandardAll() {
        List<ClassStandard> classStandardList = classStandardRepository.findAll();
        List<AdminStandardResponse> adminStandardResponseList = new ArrayList<>();
        for(ClassStandard classStandard:classStandardList){
            adminStandardResponseList.add(classRoomDto.mapToAdminStandardResponse(classStandard));
        }
        return adminStandardResponseList;
    }

    public List<AdminAddClassTeacherResponse> getTeacherListForAddClassroom() {
        List<Teacher> teacherList = teacherRepository.findAll();
        List<AdminAddClassTeacherResponse> adminAddClassTeacherResponseList = new ArrayList<>();
        for(Teacher teacher :teacherList){
            Optional<ClassRoom> optionalClassRoom = classRoomRepository.findByTeacherUserClassRoom(teacher.getTeacherUser().getId());
            if(optionalClassRoom.isPresent()){
                adminAddClassTeacherResponseList.add(teacherDto.mapToAdminAddClassTeacherResponse(teacher,
                        optionalClassRoom));
            }else {
                adminAddClassTeacherResponseList.add(teacherDto.mapToAdminAddClassTeacherResponse(teacher));
            }
        }

        return adminAddClassTeacherResponseList;
    }

    public List<AdminStudentListForAttendanceResponse> getStudentListForAttendance(Long classId) {
        Optional<ClassRoom> optionalClassRoom = classRoomRepository.findById(classId);
        List<AdminStudentListForAttendanceResponse> adminStudentListForAttendanceResponseList = new ArrayList<>();
        if(optionalClassRoom.isPresent()){
            Optional<List<Student>> optionalStudentList = studentRepository.findByClassRoom(optionalClassRoom.get().getId());
            for(Student student :optionalStudentList.get()){
                adminStudentListForAttendanceResponseList.add(studentDto.mapToAdminStudentListForAttendanceResponse(student,optionalClassRoom.get()));
            }
            return adminStudentListForAttendanceResponseList;
        }
        return null;
    }

    public Integer getTodayPresentData() {
        int count =0;
        Optional<List<AttendanceRegister>> optionalAttendanceRegister = attendanceRegisterRepository.findByTodayDate();
        if(optionalAttendanceRegister.isPresent()){
            for(AttendanceRegister attendanceRegister : optionalAttendanceRegister.get()){
                count +=1;
            }
        }
        return count;
    }
}

package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.repository.StudentRepository;
import com.restapi.request.ParentRequest;
import com.restapi.response.admin.AdminStudentListForAttendanceResponse;
import com.restapi.response.admin.AdminStudentResponse;
import com.restapi.response.admin.StudentApproveResponse;
import com.restapi.response.student.StudentAssignmentResponse;
import com.restapi.response.student.StudentAttendanceResponse;
import com.restapi.response.student.StudentClassroomStructureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDto {

    @Autowired
    private StudentRepository studentRepository;
    public Student setStudentDetails(ClassRoom classRoom, StudentStatus studentStatus, AppUser studentAppUser, ParentRequest parentRequest) {
        Student student = new Student();
        if(parentRequest.getStudentUserId()!=0){
            Optional<Student> studentFetch = studentRepository.findByUserId(parentRequest.getStudentUserId());
            if(studentFetch.get().getId()!=0){
                student.setId(studentFetch.get().getId());
            }
        }
        student.setFirstName(parentRequest.getFirstName());
        student.setLastname(parentRequest.getLastName());
        student.setDateOfBirth(parentRequest.getDateOfBirth());
        student.setGender(parentRequest.getGender());
        student.setStudentStatus(studentStatus);
        student.setStudentUser(studentAppUser);
        student.setClassRoom(classRoom);
//        System.out.println("studentCrossed");
        return student;
    }

    public Student MapToApproveForStudent(Student student, StudentStatus studentStatus, ClassRoom classRoom) {
        Student updatedStudent = student;
        updatedStudent.setStudentStatus(studentStatus);
        updatedStudent.setClassRoom(classRoom);
        System.out.println(updatedStudent);
        return updatedStudent;
    }

    public StudentApproveResponse mapToApproveResponse(Student student) {
        StudentApproveResponse studentApproveResponse = new StudentApproveResponse();
        studentApproveResponse.setStudentUserId(student.getStudentUser().getId());
        studentApproveResponse.setStudentName(student.getFirstName());
        studentApproveResponse.setStudentUsername(student.getStudentUser().getUsername());
        studentApproveResponse.setStudentClassId(student.getClassRoom().getId());
        studentApproveResponse.setStudentStatusId(student.getStudentStatus().getId());
        return studentApproveResponse;
    }

    public AdminStudentResponse mapToResponse(Student student, Parent parent) {
        AdminStudentResponse adminStudentResponse = new AdminStudentResponse();
        adminStudentResponse.setId(student.getId());
        adminStudentResponse.setStudentUserId(student.getStudentUser().getId());
        adminStudentResponse.setFirstName(student.getFirstName());
        adminStudentResponse.setLastName(student.getLastname());
        adminStudentResponse.setClassName(student.getClassRoom().getClassStandard().getStandard());
        adminStudentResponse.setClassId(student.getClassRoom().getId());
        adminStudentResponse.setGender(student.getGender());
        adminStudentResponse.setFatherName(parent.getFatherName());
        adminStudentResponse.setMotherName(parent.getMotherName());
        adminStudentResponse.setPhoneNumber(parent.getFatherPhoneNumber());
        adminStudentResponse.setStudentStatusId(student.getStudentStatus().getId());
        adminStudentResponse.setDateOfJoin(student.getDateOfJoin());
        return adminStudentResponse;
    }

    public StudentAttendanceResponse mapToStudentAttendanceResponse(List<AttendanceRegister> attendanceRegister, Long userId) {
        StudentAttendanceResponse studentAttendanceResponse = new StudentAttendanceResponse();
        Optional<Student> student = studentRepository.findByUserId(userId);
        if(student.isPresent()){
            studentAttendanceResponse.setJoinedDate(student.get().getDateOfJoin());
            List<LocalDate> attendance = new ArrayList<>();
            for (AttendanceRegister attendanceRegisters  :attendanceRegister){
                attendance.add(attendanceRegisters.getDate());
            }
            studentAttendanceResponse.setDate(attendance);
            return studentAttendanceResponse;
        }
        return null;
    }

    public StudentClassroomStructureResponse mapToClassRoomStructureResponse(ClassRoom classRoom, Teacher classTeacher, Teacher tamilTeacher, Teacher englishTeacher, Teacher mathsTeacher, Teacher scienceTeacher, Teacher socialTeacher) {
        StudentClassroomStructureResponse studentClassroomStructureResponse = new StudentClassroomStructureResponse();
        studentClassroomStructureResponse.setClassId(classRoom.getId());
        studentClassroomStructureResponse.setClassStandard(classRoom.getClassStandard().getStandard());
        studentClassroomStructureResponse.setClassInChargeName(classTeacher.getFirstName());
        studentClassroomStructureResponse.setClassInChargeId(classTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setClassInChargePhone(classTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setClassInChargeEmail(classTeacher.getEmail());
        studentClassroomStructureResponse.setTamilTeacherId(tamilTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setTamilTeacherName(tamilTeacher.getFirstName());
        studentClassroomStructureResponse.setTamilTeacherPhone(tamilTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setTamilTeacherEmail(tamilTeacher.getEmail());

        studentClassroomStructureResponse.setEnglishTeacherId(englishTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setEnglishTeacherName(englishTeacher.getFirstName());
        studentClassroomStructureResponse.setEnglishTeacherPhone(englishTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setEnglishTeacherEmail(englishTeacher.getEmail());

        studentClassroomStructureResponse.setMathsTeacherId(mathsTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setMathsTeacherName(mathsTeacher.getFirstName());
        studentClassroomStructureResponse.setMathsTeacherPhone(mathsTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setMathsTeacherEmail(mathsTeacher.getEmail());

        studentClassroomStructureResponse.setScienceTeacherId(scienceTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setScienceTeacherName(scienceTeacher.getFirstName());
        studentClassroomStructureResponse.setScienceTeacherPhone(scienceTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setScienceTeacherEmail(scienceTeacher.getEmail());

        studentClassroomStructureResponse.setSocialTeacherId(socialTeacher.getTeacherUser().getId());
        studentClassroomStructureResponse.setSocialTeacherName(socialTeacher.getFirstName());
        studentClassroomStructureResponse.setSocialTeacherPhone(socialTeacher.getPhoneNumber());
        studentClassroomStructureResponse.setSocialTeacherEmail(socialTeacher.getEmail());

        return studentClassroomStructureResponse;
    }

    public StudentAssignmentResponse mapToStudentAssignmentResponse(Assignment assignment, AssignmentGrade optionalAssignmentGrade) {
        StudentAssignmentResponse studentAssignmentResponse = new StudentAssignmentResponse();
        studentAssignmentResponse.setAssignmentId(assignment.getId());
        studentAssignmentResponse.setAssignmentType(assignment.getAssignmentType().getType());
        studentAssignmentResponse.setComments(assignment.getComments());
        studentAssignmentResponse.setSubjects(assignment.getSubjectAssignment().getSubject());
        studentAssignmentResponse.setCreatedDate(assignment.getCreatedDate());
        studentAssignmentResponse.setDueDate(assignment.getDeadline());
        studentAssignmentResponse.setObtainedMark(Long.valueOf(optionalAssignmentGrade.getMarksObtained()));
        studentAssignmentResponse.setSubmitComments(optionalAssignmentGrade.getComments());
        studentAssignmentResponse.setTotalMark(Long.valueOf(assignment.getTotalGrade()));
        studentAssignmentResponse.setMinMark(Long.valueOf(assignment.getMinScore()));
        return studentAssignmentResponse;
    }

    public StudentAssignmentResponse mapToStudentAssignmentResponse(Assignment assignment) {
        StudentAssignmentResponse studentAssignmentResponse = new StudentAssignmentResponse();
        studentAssignmentResponse.setAssignmentId(assignment.getId());
        studentAssignmentResponse.setAssignmentType(assignment.getAssignmentType().getType());
        studentAssignmentResponse.setComments(assignment.getComments());
        studentAssignmentResponse.setSubjects(assignment.getSubjectAssignment().getSubject());
        studentAssignmentResponse.setCreatedDate(assignment.getCreatedDate());
        studentAssignmentResponse.setDueDate(assignment.getDeadline());
        studentAssignmentResponse.setTotalMark(Long.valueOf(assignment.getTotalGrade()));
        studentAssignmentResponse.setMinMark(Long.valueOf(assignment.getMinScore()));
        return studentAssignmentResponse;
    }

    public AdminStudentListForAttendanceResponse mapToAdminStudentListForAttendanceResponse(Student student, ClassRoom classRoom) {
        AdminStudentListForAttendanceResponse adminStudentListForAttendanceResponse = new AdminStudentListForAttendanceResponse();
        adminStudentListForAttendanceResponse.setStudentId(student.getStudentUser().getId());
        adminStudentListForAttendanceResponse.setFirstName(student.getFirstName());
        adminStudentListForAttendanceResponse.setLastName(student.getLastname());
        adminStudentListForAttendanceResponse.setClassId(classRoom.getId());
        adminStudentListForAttendanceResponse.setStandard(classRoom.getClassStandard().getStandard());
        return adminStudentListForAttendanceResponse;
    }
}

package com.restapi.service;

import com.restapi.dto.ClassRoomDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.ClassRoom;
import com.restapi.model.ClassStandard;
import com.restapi.model.Student;
import com.restapi.repository.*;
import com.restapi.request.ClassRoomRequest;
import com.restapi.response.ClassRoomResponse;
import com.restapi.response.classroom.ClassListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private ClassStandardRepository classStandardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomDto classRoomDto;


    public ClassRoomResponse createClassRoom(ClassRoomRequest classRoomRequest) {
        AppUser classTeacherUser = userRepository.findById(classRoomRequest.getTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getTeacherUserId()));
//        Teacher classTeacher = teacherRepository.findByUserId(classRoomRequest.getTeacherUserId());

        AppUser tamilTeacherUser = userRepository.findById(classRoomRequest.getTamilTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getTamilTeacherUserId()));

        AppUser englishTeacherUser = userRepository.findById(classRoomRequest.getEnglishTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getEnglishTeacherUserId()));

        AppUser mathsTeacherUser = userRepository.findById(classRoomRequest.getMathsTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getMathsTeacherUserId()));

        AppUser scienceTeacherUser = userRepository.findById(classRoomRequest.getScienceTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getScienceTeacherUserId()));

        AppUser socialTeacherUser = userRepository.findById(classRoomRequest.getSocialTeacherUserId())
                .orElseThrow(() -> new ResourceNotFoundException("teacherId", "teacherId", classRoomRequest.getSocialTeacherUserId()));
        ClassStandard classStandard = classStandardRepository.findById(classRoomRequest.getStandardId())
                .orElseThrow(() -> new ResourceNotFoundException("standardId", "standardId", classRoomRequest.getStandardId()));
        ClassRoom classRoom = new ClassRoom();
        if (classRoomRequest.getClassRoomId() != null) {

            classRoom.setId(classRoomRequest.getClassRoomId());
        }
        classRoom.setClassStandard(classStandard);
        classRoom.setTeacherUserClassRoom(classTeacherUser);
        classRoom.setTamilTeacherUser(tamilTeacherUser);
        classRoom.setEnglishTeacherUser(englishTeacherUser);
        classRoom.setMathsTeacherUser(mathsTeacherUser);
        classRoom.setScienceTeacherUser(scienceTeacherUser);
        classRoom.setSocialTeacherUser(socialTeacherUser);
        classRoom = classRoomRepository.save(classRoom);
        return classRoomDto.mapToClassRoomResponse(classRoom);
    }

    public ClassRoom findById(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("classId", "classId", id));

        return classRoom;
    }

    public List<Student> studentFromClass(Long classId) {
        Optional<List<Student>> students = studentRepository.findByClassRoom(classId);
        return students.get();
    }

    public List<ClassListResponse> standardList() {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        List<ClassListResponse> classListResponseList = new ArrayList<>();
        for (ClassRoom classRoom : classRooms) {
            classListResponseList.add(classRoomDto.mapToClassList(classRoom));
        }
        return classListResponseList;
    }

}

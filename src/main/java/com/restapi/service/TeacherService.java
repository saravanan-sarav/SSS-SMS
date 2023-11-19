package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.ClassRoom;
import com.restapi.model.Student;
import com.restapi.model.Teacher;
import com.restapi.repository.ClassRoomRepository;
import com.restapi.repository.StudentRepository;
import com.restapi.repository.TeacherRepository;
import com.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Teacher findById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findByUserId(id);
        return teacher.get();
    }

    public List<Student> getMyclass(Long teacherUserId) {
        AppUser teacherUser = userRepository.findById(teacherUserId).orElseThrow(() -> new ResourceNotFoundException("userId", "userId", teacherUserId));
        if (teacherUser != null) {
            Optional<ClassRoom> classRoom = classRoomRepository.findByTeacherUserClassRoom(teacherUser.getId());
            if (classRoom.isPresent()) {
                Optional<List<Student>> students = studentRepository.findByClassRoom(classRoom.get().getId());
                return students.get();
            }
        }
        return null;
    }
}

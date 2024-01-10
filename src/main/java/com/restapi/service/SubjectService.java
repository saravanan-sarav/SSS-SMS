package com.restapi.service;

import com.restapi.dto.SubjectDto;
import com.restapi.model.Subject;
import com.restapi.repository.SubjectRepository;
import com.restapi.response.subject.SubjectResponse;
import com.restapi.response.subject.TeacherCountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectDto subjectDto;

    public List<SubjectResponse> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectResponse> subjectResponseList = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectResponseList.add(subjectDto.mapToSubjectResponse(subject));
        }
        return subjectResponseList;
    }


    public TeacherCountResponse getTeacherCount() {
        List<Object[]> objectList = subjectRepository.selectTeacherCountWithSubjects();
        List<String> subject = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        for (Object[] result : objectList) {
            String subjectName = (String) result[0];
            Long teacherCount = (Long) result[1];
            subject.add(subjectName);
            count.add(teacherCount);
        }
        TeacherCountResponse teacherCountResponse = new TeacherCountResponse(subject,count);

        return teacherCountResponse;
    }
}

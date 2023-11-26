package com.restapi.dto;

import com.restapi.model.Subject;
import com.restapi.response.subject.SubjectResponse;
import org.springframework.stereotype.Component;

@Component
public class SubjectDto {

    public SubjectResponse mapToSubjectResponse(Subject subject) {
        SubjectResponse subjectResponse= new SubjectResponse();
        subjectResponse.setSubjectId(subject.getId());
        subjectResponse.setSubject(subject.getSubject());
        return subjectResponse;
    }
}

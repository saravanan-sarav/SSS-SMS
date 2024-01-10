package com.restapi.response.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeacherCountResponse {
    private List<String> subject;
    private List<Long> count;
}

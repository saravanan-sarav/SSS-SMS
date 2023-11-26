package com.restapi.response.assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentTypeResponse {
    private Long assignmentId;
    private String assignmentType;
}

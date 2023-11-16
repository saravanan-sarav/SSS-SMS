package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentResponse {
    private Long parentId;
    private Long studentId;

    private Long studentUserId;
    private Long parentUserId;

    private Long addressId;

    private String studentName;
    private String parentName;

    private String studentUsername;
    private String parentUsername;

}

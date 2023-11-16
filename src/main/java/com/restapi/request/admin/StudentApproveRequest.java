package com.restapi.request.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentApproveRequest {
    public Long studentUserId;
    public Long studentStatusId;
    public Long classId;
}

package com.srms.student_result_management.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    private String username;
    private String password;
    private String role;
    private String rollNumber;
    private  String dob;
}

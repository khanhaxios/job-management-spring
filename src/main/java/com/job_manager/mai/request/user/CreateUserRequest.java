package com.job_manager.mai.request.user;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CreateUserRequest extends UserRequest {
    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Birthday is required")

    private Date birthday;

    @NotBlank(message = "Department is required")

    private String department;

    @NotBlank(message = "Phone is required")
    private String phone;
}

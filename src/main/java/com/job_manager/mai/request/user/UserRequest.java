package com.job_manager.mai.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Account id is required")
    private String accountId;
}

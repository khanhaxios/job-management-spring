package com.job_manager.mai.request.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PermissionRequest {
    private String name;
    private String description;
    private List<Long> destroyIds = new ArrayList<>();
}

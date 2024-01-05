package com.job_manager.mai.request.role;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateRoleRequest extends RoleRequest {

    private String roleName;
    private String description;

    private Set<Long> permissionIds;

}

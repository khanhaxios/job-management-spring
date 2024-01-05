package com.job_manager.mai.service.role;

import com.job_manager.mai.request.role.CreateRoleRequest;
import com.job_manager.mai.request.role.DeleteRoleRequest;
import com.job_manager.mai.request.role.RoleRequest;
import com.job_manager.mai.request.role.UpdateRoleRequest;
import com.job_manager.mai.service.inteface.IBaseService;

public interface RoleService extends IBaseService<RoleRequest, CreateRoleRequest, UpdateRoleRequest, DeleteRoleRequest, Long> {
}

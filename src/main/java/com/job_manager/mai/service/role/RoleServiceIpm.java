package com.job_manager.mai.service.role;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.exception.RoleExists;
import com.job_manager.mai.model.Role;
import com.job_manager.mai.repository.PermissionRepository;
import com.job_manager.mai.repository.RoleRepository;
import com.job_manager.mai.request.role.CreateRoleRequest;
import com.job_manager.mai.request.role.DeleteRoleRequest;
import com.job_manager.mai.request.role.UpdateRoleRequest;
import com.job_manager.mai.response.role.IRoleResponser;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RoleServiceIpm extends BaseService implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final IRoleResponser iRoleResponser;

    @Override
    public ResponseEntity<?> store(CreateRoleRequest request) throws Exception {
        if (existsByName(request.getRoleName())) {
            throw new RoleExists(Messages.ROLE_EXISTS);
        }
        Role newRole = getMapper().map(request, Role.class);
        if (!request.getPermissionIds().isEmpty()) {
            for (long id : request.getPermissionIds()) {
                newRole.addPerm(permissionRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(Messages.PERMISSION_NOTFOUND)));
            }
        }
        return ApiResponseHelper.success(iRoleResponser.mapTo(roleRepository.saveAndFlush(newRole)));
    }

    private boolean existsByName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }

    private Role findById(long id) throws Exception {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(Messages.ROLE_NOT_FOUND));
    }

    private List<Role> findAllByIds(Set<Long> ids) {
        return roleRepository.findAllById(ids);
    }

    @Override
    public ResponseEntity<?> edit(UpdateRoleRequest request, Long aLong) throws Exception {
        Role role = findById(aLong);

        if (existsByName(request.getRoleName())) {
            throw new RoleExists(Messages.ROLE_EXISTS);
        }

        BeanUtils.copyProperties(request, role, getNullPropertyNames(request));

        if (!request.getPermissionIds().isEmpty()) {
            for (long id : request.getPermissionIds()) {
                role.addPerm(permissionRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(Messages.PERMISSION_NOTFOUND)));
            }
        }
        return ApiResponseHelper.success(iRoleResponser.mapTo(roleRepository.saveAndFlush(role)));
    }

    @Override
    public ResponseEntity<?> destroy(Long aLong) throws Exception {
        Role role = findById(aLong);
        role.getAccounts().clear();
        role.getPermissions().clear();
        roleRepository.delete(role);
        return ApiResponseHelper.success();
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteRoleRequest request) throws Exception {
        List<Role> roles = findAllByIds(request.getRoleIds());
        roles.forEach((r) -> {
            r.getAccounts().clear();
            r.getPermissions().clear();
            roleRepository.delete(r);
        });
        return ApiResponseHelper.success();
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ApiResponseHelper.success(iRoleResponser.mapTo(roleRepository.findAll(pageable)));
    }

    @Override
    public ResponseEntity<?> getById(Long aLong) throws Exception {
        return ApiResponseHelper.success(iRoleResponser.mapTo(findById(aLong)));
    }

    @Override
    public ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> SearchAndSortByProperties(Pageable pageable, String searchProperties, String sortProperties) throws Exception {
        return null;
    }
}

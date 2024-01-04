package com.job_manager.mai.service.permission;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.exception.PermissionNotFound;
import com.job_manager.mai.model.Permission;
import com.job_manager.mai.repository.PermissionRepository;
import com.job_manager.mai.request.permission.PermissionRequest;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service
//@Transactional
//@RequiredArgsConstructor
//public class PermissionServiceIpm extends BaseService implements PermissionService<PermissionRequest, Long> {
//    private final PermissionRepository permissionRepository;
//
//    @Override
//    public ResponseEntity<?> store(PermissionRequest permissionRequest) throws Exception {
//        boolean exited = permissionRepository.findByName(permissionRequest.getName()).isPresent();
//        if (exited) {
//            return ApiResponseHelper.resp(null, HttpStatus.BAD_REQUEST, Messages.PERMISSION_EXITED);
//        }
//        return ApiResponseHelper.resp(permissionRepository.saveAndFlush(getMapper().map(permissionRequest, Permission.class)), HttpStatus.OK, Messages.DEFAULT_SUCCESS_MESSAGE);
//    }
//
//    @Override
//    public ResponseEntity<?> edit(PermissionRequest permissionRequest, Long aLong) throws Exception {
//        Permission permission = permissionRepository.findById(aLong).orElseThrow(() -> new PermissionNotFound(Messages.PERMISSION_NOTFOUND));
//        permission.setDescription(permissionRequest.getDescription());
//        return ApiResponseHelper.resp(permissionRepository.saveAndFlush(permission), HttpStatus.OK, Messages.DEFAULT_SUCCESS_MESSAGE);
//    }
//
//    @Override
//    public ResponseEntity<?> destroy(Long aLong) throws Exception {
//        permissionRepository.deleteById(aLong);
//        return ApiResponseHelper.success();
//    }
//
//    @Override
//    public ResponseEntity<?> destroyAll(PermissionRequest permissionRequest) throws Exception {
//        permissionRepository.deleteAllById(permissionRequest.getDestroyIds());
//        return ApiResponseHelper.success();
//    }
//
//
//    @Override
//    public ResponseEntity<?> getAll(Pageable pageable) {
//        return ApiResponseHelper.success(permissionRepository.findAll(pageable));
//    }
//
//    @Override
//    public ResponseEntity<?> getById(Long aLong) {
//        return ApiResponseHelper.success(permissionRepository.findById(aLong));
//    }
//
//    @Override
//    public ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception {
//        return ApiResponseHelper.success(permissionRepository.findByNameContaining(pageable, name));
//    }
//}

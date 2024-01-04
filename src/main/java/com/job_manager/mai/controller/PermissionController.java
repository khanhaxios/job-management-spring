package com.job_manager.mai.controller;

import com.job_manager.mai.contrains.Permission;
import com.job_manager.mai.controller.base.BaseController;
import com.job_manager.mai.controller.inteface.IBaseController;
import com.job_manager.mai.repository.AccountRepository;
import com.job_manager.mai.request.permission.PermissionRequest;
//import com.job_manager.mai.service.permission.PermissionService;
import com.job_manager.mai.util.ApiResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/permission")
//@RequiredArgsConstructor
//public class PermissionController implements IBaseController<PermissionRequest, Long> {
//
//    private final PermissionService<PermissionRequest, Long> permissionService;
//
//    private final BaseController baseController;
//
//    @PostMapping
//    @Override
//    public ResponseEntity<?> add(@RequestBody PermissionRequest requestBody) {
//        try {
//            baseController.processPermission(Permission.MANAGE_PERMISSION_CREATE);
//            return permissionService.store(requestBody);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @Override
//    public ResponseEntity<?> update(PermissionRequest requestBody, @PathVariable(name = "id") Long Id) {
//        try {
//            return permissionService.edit(requestBody, Id);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @Override
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") Long Id) {
//        try {
//            return permissionService.destroy(Id);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @DeleteMapping
//    @Override
//    public ResponseEntity<?> deleteAll(@RequestBody PermissionRequest permissionRequest) {
//        try {
//            return permissionService.destroyAll(permissionRequest);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @Override
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable(name = "id") Long aLong) {
//        try {
//            return permissionService.getById(aLong);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @Override
//    @GetMapping()
//    public ResponseEntity<?> getAll(Pageable pageable) {
//        try {
//            return permissionService.getAll(pageable);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> search(String query) {
//        return null;
//    }
//
//    @Override
//    @GetMapping("/search")
//    public ResponseEntity<?> searchByName(Pageable pageable, @RequestParam(name = "name") String name) {
//        try {
//            return permissionService.searchByName(pageable, name);
//        } catch (Exception e) {
//            return ApiResponseHelper.fallback(e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> sortByName(String name) {
//        return null;
//    }
//}

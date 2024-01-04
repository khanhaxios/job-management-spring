package com.job_manager.mai.controller;

import com.job_manager.mai.contrains.Permission;
import com.job_manager.mai.controller.base.BaseController;
import com.job_manager.mai.controller.inteface.IBaseController;
import com.job_manager.mai.repository.AccountRepository;
import com.job_manager.mai.request.user.CreateUserRequest;
import com.job_manager.mai.request.user.DeleteUserRequest;
import com.job_manager.mai.request.user.UpdateUserRequest;
import com.job_manager.mai.request.user.UserRequest;
import com.job_manager.mai.service.user.UserServiceIpm;
import com.job_manager.mai.util.ApiResponseHelper;
import com.job_manager.mai.util.SecurityHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements IBaseController<UserRequest, CreateUserRequest, UpdateUserRequest, DeleteUserRequest, String> {

    private final BaseController baseController;

    private final UserServiceIpm userServiceIpm;

    @PostMapping
    @Override
    public ResponseEntity<?> add(@Valid @RequestBody CreateUserRequest requestBody) {
        try {
            if (!baseController.checkIfSelf(requestBody.getAccountId(), SecurityHelper.getLoggedUser())) {
                baseController.processPermission(Permission.MANAGE_USER_CREATE);
            }
            return userServiceIpm.store(requestBody);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @Override
    public ResponseEntity<?> update(UpdateUserRequest requestBody, String Id) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String Id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAll(DeleteUserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(String s) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> search(String query) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchByName(Pageable pageable, String name) {
        return null;
    }

    @Override
    public ResponseEntity<?> sortByName(String name) {
        return null;
    }
}

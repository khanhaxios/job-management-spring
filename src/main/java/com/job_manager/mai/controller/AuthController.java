package com.job_manager.mai.controller;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.request.account.AccountRequest;
import com.job_manager.mai.request.account.RegisterRequest;
import com.job_manager.mai.request.account.VerifyEmailRequest;
import com.job_manager.mai.service.account.AccountService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/public/auth")
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AccountRequest accountRequest) {
        try {
            return ApiResponseHelper.resp(accountService.login(accountRequest), HttpStatus.OK, Messages.DEFAULT_SUCCESS_MESSAGE);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        try {
            return accountService.verifyEmail(verifyEmailRequest.getEmail(), verifyEmailRequest.getCode());
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @GetMapping("/request-new-code")
    public ResponseEntity<?> newVerifyCode(@RequestParam(name = "email") String email) {
        try {
            return accountService.requestNewVerifyCode(email);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            return ApiResponseHelper.resp(accountService.register(registerRequest), HttpStatus.OK, Messages.DEFAULT_SUCCESS_MESSAGE);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }
}

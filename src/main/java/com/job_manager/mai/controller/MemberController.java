package com.job_manager.mai.controller;

import com.job_manager.mai.controller.inteface.IBaseController;
import com.job_manager.mai.request.member.CreateMemberRequest;
import com.job_manager.mai.request.member.DeleteMemberRequest;
import com.job_manager.mai.request.member.MemberRequest;
import com.job_manager.mai.request.member.UpdateMemberRequest;
import com.job_manager.mai.service.member.MemberService;
import com.job_manager.mai.util.ApiResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController implements IBaseController<MemberRequest, CreateMemberRequest, UpdateMemberRequest, DeleteMemberRequest, String> {
    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<?> findMemberByKey(@RequestParam(name = "query") String query) {
        try {
            return memberService.getMemberByKey(query);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @Override
    public ResponseEntity<?> add(CreateMemberRequest requestBody) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(UpdateMemberRequest requestBody, String Id) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String Id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAll(DeleteMemberRequest request) {
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

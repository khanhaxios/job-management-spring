package com.job_manager.mai.service.member;

import com.job_manager.mai.model.User;
import com.job_manager.mai.repository.MemberRepository;
import com.job_manager.mai.repository.UserRepository;
import com.job_manager.mai.request.member.CreateMemberRequest;
import com.job_manager.mai.request.member.DeleteMemberRequest;
import com.job_manager.mai.request.member.UpdateMemberRequest;
import com.job_manager.mai.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceIpm implements MemberService {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> store(CreateMemberRequest request) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> edit(UpdateMemberRequest request, String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroy(String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteMemberRequest request) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> SearchAndSortByProperties(Pageable pageable, String searchProperties, String sortProperties) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> getMemberByKey(String query) {
        Optional<User> user = userRepository.findByEmailOrPhone(query);
        if (user.isPresent()) {
            return ApiResponseHelper.success(memberRepository.findByUser(user.get()));
        }
        return ApiResponseHelper.success(new HashMap<>());
    }
}

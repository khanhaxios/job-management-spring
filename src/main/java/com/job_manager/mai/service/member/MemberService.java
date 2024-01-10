package com.job_manager.mai.service.member;

import com.job_manager.mai.request.member.CreateMemberRequest;
import com.job_manager.mai.request.member.DeleteMemberRequest;
import com.job_manager.mai.request.member.MemberRequest;
import com.job_manager.mai.request.member.UpdateMemberRequest;
import com.job_manager.mai.service.inteface.IBaseService;
import org.springframework.http.ResponseEntity;

public interface MemberService extends IBaseService<MemberRequest, CreateMemberRequest, UpdateMemberRequest, DeleteMemberRequest, String> {

    ResponseEntity<?> getMemberByKey(String query);
}

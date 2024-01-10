package com.job_manager.mai.repository;

import com.job_manager.mai.model.Member;
import com.job_manager.mai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUser(User user);
}

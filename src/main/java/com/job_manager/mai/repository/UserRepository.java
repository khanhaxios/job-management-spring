package com.job_manager.mai.repository;

import com.job_manager.mai.model.Account;
import com.job_manager.mai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByPhone(String phone);
    @Query(value = "select u from User u where u.email = :key or u.phone = :key")
    Optional<User> findByEmailOrPhone(String key);
}

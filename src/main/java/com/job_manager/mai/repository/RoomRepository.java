package com.job_manager.mai.repository;

import com.job_manager.mai.model.Room;
import com.job_manager.mai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Set<Room> findByMembers(User members);
}

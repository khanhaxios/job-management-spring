package com.job_manager.mai.service.room;

import com.job_manager.mai.request.room.CreateRoomRequest;
import com.job_manager.mai.request.room.DeleteRoomRequest;
import com.job_manager.mai.request.room.RoomRequest;
import com.job_manager.mai.request.room.UpdateRoomRequest;
import com.job_manager.mai.service.inteface.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RoomService extends IBaseService<RoomRequest, CreateRoomRequest, UpdateRoomRequest, DeleteRoomRequest, String> {

    ResponseEntity<?> getByMember(String memberId);

    ResponseEntity<?> getMessageById(Pageable pageable, String roomId);
}

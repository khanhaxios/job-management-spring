package com.job_manager.mai.service.room;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.model.Message;
import com.job_manager.mai.model.Room;
import com.job_manager.mai.repository.RoomRepository;
import com.job_manager.mai.repository.UserRepository;
import com.job_manager.mai.request.room.CreateRoomRequest;
import com.job_manager.mai.request.room.DeleteRoomRequest;
import com.job_manager.mai.request.room.UpdateRoomRequest;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceIpm extends BaseService implements RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> store(CreateRoomRequest request) throws Exception {
        Room room = getMapper().map(request, Room.class);
        request.getInitMember().forEach((m) -> {
            room.addMember(userRepository.findById(m).orElse(null));
        });
        return ApiResponseHelper.success(roomRepository.saveAndFlush(room));
    }

    @Override
    public ResponseEntity<?> edit(UpdateRoomRequest request, String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroy(String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteRoomRequest request) throws Exception {
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
    public ResponseEntity<?> getByMember(String memberId) {
        return ApiResponseHelper.success(roomRepository.findByMembers(userRepository.findById(memberId).orElse(null)));
    }

    @Override
    public ResponseEntity<?> getMessageById(Pageable pageable, String roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new UsernameNotFoundException(Messages.ROOM_NOT_FOUND));
        List<Message> messages = new ArrayList<>(room.getMessages());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), messages.size());
        Page<Message> messagePage = new PageImpl<>(messages.subList(start, end), pageable, messages.size());
        return ApiResponseHelper.success(messagePage);
    }
}

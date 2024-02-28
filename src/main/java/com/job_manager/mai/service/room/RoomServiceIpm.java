package com.job_manager.mai.service.room;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.model.Message;
import com.job_manager.mai.model.Room;
import com.job_manager.mai.model.User;
import com.job_manager.mai.repository.RoomRepository;
import com.job_manager.mai.repository.UserRepository;
import com.job_manager.mai.request.room.CreateRoomRequest;
import com.job_manager.mai.request.room.DeleteRoomRequest;
import com.job_manager.mai.request.room.UpdateRoomRequest;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Room room = getMapper().map(request, Room.class);
        request.getInitMember().forEach((m) -> {
            room.addMember(userRepository.findById(m).orElse(null));
        });
        return ApiResponseHelper.success(roomRepository.saveAndFlush(room));
    }

    @Override
    public ResponseEntity<?> destroy(String s) throws Exception {
        return getResponseEntity(s);
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteRoomRequest request) throws Exception {
        for (String id : request.getDeleteIds()) {
            return getResponseEntity(id);
        }
        return ApiResponseHelper.success();
    }

    private ResponseEntity<?> getResponseEntity(String id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Room not found"));
        room.setLeader(null);
        room.setSubLeader(null);
        room.setMembers(new HashSet<>());
        room.setBannedUser(new HashSet<>());
        room.setMedia(new HashSet<>());
        room.setTags(new HashSet<>());
        room.setMessages(new HashSet<>());
        roomRepository.saveAndFlush(room);
        roomRepository.delete(room);
        return ApiResponseHelper.success();
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ApiResponseHelper.success(roomRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> getById(String s) throws Exception {
        return ApiResponseHelper.success(roomRepository.findById(s));
    }

    @Override
    public ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception {
        return ApiResponseHelper.success(roomRepository.findByRoomNameContaining(pageable, name));
    }

    @Override
    public ResponseEntity<?> SearchAndSortByProperties(Pageable pageable, String searchProperties, String sortProperties) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> getByMember(String memberId) {
        List<Room> rooms = roomRepository.findAll();
        User user = userRepository.findById(memberId).orElseThrow(()->new UsernameNotFoundException(Messages.USER_NOT_FOUND));
        Set<Room> roomByMember = new HashSet<>();
        for (Room room : rooms){
            if (room.getMembers().contains(user)){
                roomByMember.add(room);
            }
        }
        return ApiResponseHelper.success(roomByMember);
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

    @Override
    public ResponseEntity<?> getAllByUser(Pageable pageable, String userId) {
        return null;
    }
}

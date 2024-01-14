package com.job_manager.mai.controller;

import com.job_manager.mai.model.Message;
import com.job_manager.mai.request.room.UserMessageRequest;
import com.job_manager.mai.service.message.MessageService;
import com.job_manager.mai.service.room.RoomService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {

    private final MessageService messageService;
    private final RoomService roomService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody UserMessageRequest userMessageRequest) {
        try {
            return messageService.store(userMessageRequest);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberRooms(@PathVariable(name = "memberId") String memberId) {
        try {
            return roomService.getByMember(memberId);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getMessageByRoom(@PathVariable(name = "roomId") String roomId, Pageable pageable) {
        try {
            return roomService.getMessageById(pageable, roomId);
        } catch (Exception e) {
            return ApiResponseHelper.fallback(e);
        }
    }
}

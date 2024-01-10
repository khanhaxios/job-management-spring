package com.job_manager.mai.service.message;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.exception.UserNotFoundException;
import com.job_manager.mai.model.Member;
import com.job_manager.mai.model.Message;
import com.job_manager.mai.model.Room;
import com.job_manager.mai.repository.MemberRepository;
import com.job_manager.mai.repository.MessageRepository;
import com.job_manager.mai.repository.RoomRepository;
import com.job_manager.mai.request.messages.CreateMessageRequest;
import com.job_manager.mai.request.messages.DeleteMessageRequest;
import com.job_manager.mai.request.messages.UpdateMessageRequest;
import com.job_manager.mai.service.base.BaseService;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceIpm extends BaseService implements MessageService {

    private final MemberRepository memberRepository;

    private final MessageRepository messageRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RoomRepository roomRepository;

    @Override
    public ResponseEntity<?> store(CreateMessageRequest request) throws Exception {
        Message message = getMapper().map(request, Message.class);
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));
        message.setSender(member);
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new UserNotFoundException(String.format(Messages.ROOM_NOT_FOUND, request.getRoomId())));
        message.setRoom(room);
        message.setSentAt(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        simpMessagingTemplate.convertAndSend("/all/rooms", savedMessage);
        return ApiResponseHelper.success(savedMessage);
    }


    @Override
    public ResponseEntity<?> edit(UpdateMessageRequest request, String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroy(String s) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> destroyAll(DeleteMessageRequest request) throws Exception {
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
    public void readMessage(String memberId, String messageId) throws Exception {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new UserNotFoundException(Messages.MESSAGE_NOT_FOUND));
        message.getReaders().add(memberRepository.findById(memberId).orElse(null));
        messageRepository.save(message);
    }
}

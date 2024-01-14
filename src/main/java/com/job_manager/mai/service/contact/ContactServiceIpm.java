package com.job_manager.mai.service.contact;

import com.job_manager.mai.contrains.ContactResponseCommand;
import com.job_manager.mai.contrains.ContactStatus;
import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.model.Contact;
import com.job_manager.mai.model.User;
import com.job_manager.mai.pusher.ContactPusher;
import com.job_manager.mai.repository.ContactRepository;
import com.job_manager.mai.repository.UserRepository;
import com.job_manager.mai.util.ApiResponseHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ContactServiceIpm implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;


    private final ContactPusher contactPusher;

    @Override
    public ResponseEntity<?> sendAddContactRequest(String from, String to) throws Exception {

        User owner = userRepository.findById(from).orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND));
        User relate = userRepository.findById(to).orElseThrow(() -> new UsernameNotFoundException(Messages.INVATOR_NOT_FOUND));
        Contact contact = contactRepository.findByOwnerAndRelate(owner, relate).orElse(new Contact());
        contact.setRelate(relate);
        contact.setOwner(owner);
        contact.setStatus(ContactStatus.WAITING_RESPONSE);
        Contact savedContact = contactRepository.save(contact);
        contactPusher.pushNewContactRequest(savedContact);
        return ApiResponseHelper.success();
    }

    @Override
    public ResponseEntity<?> responseContactRequest(long contactId, int command) throws Exception {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new UsernameNotFoundException(Messages.CONTACT_NOT_FOUND));
        if (command == ContactResponseCommand.DENIED.getValue()) {
            contact.setStatus(ContactStatus.DENIED);
        }
        contact.setStatus(ContactStatus.BE_FRIEND);
        contactRepository.save(contact);
        contactPusher.pushContactRequestResponse(contact);
        return ApiResponseHelper.success(contact);
    }

    @Override
    public ResponseEntity<?> getAllContactByUser(Pageable pageable, String userId) throws Exception {
        return ApiResponseHelper.success(contactRepository.findAllByOwnerAndStatus(pageable, userRepository.findById(userId).orElse(null), ContactStatus.BE_FRIEND));
    }

    @Override
    public ResponseEntity<?> getAllAddContactRequestByUser(Pageable pageable, String userId) throws Exception {
        return ApiResponseHelper.success(contactRepository.findAllByOwnerAndStatus(pageable, userRepository.findById(userId).orElse(null), ContactStatus.WAITING_RESPONSE));
    }
}

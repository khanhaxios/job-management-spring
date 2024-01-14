package com.job_manager.mai.listener;

import com.job_manager.mai.contrains.ContactEvent;
import com.job_manager.mai.event.NewContactRequestEvent;
import com.job_manager.mai.event.ResponseContactRequestEvent;
import com.job_manager.mai.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactListener {

    @AllArgsConstructor
    @Data
    public static class ContactEventResponse {
        private Contact contact;
        private int type;
    }

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void responseContactRequestEvent(ResponseContactRequestEvent event) {
        convertAndSendEvent(new ContactEventResponse(event.getContact(), ContactEvent.RESPONSE_REQUEST.getValue()), event.getContact().getOwner().getEmail());
    }

    public void convertAndSendEvent(ContactEventResponse contactEventResponse, String to) {
        simpMessagingTemplate.convertAndSendToUser(to, "/contact", contactEventResponse);
    }

    @EventListener
    public void newContactRequestEvent(NewContactRequestEvent event) {
        convertAndSendEvent(new ContactEventResponse(event.getContact(), ContactEvent.NEW_REQUEST.getValue()), event.getContact().getRelate().getEmail());
    }

}

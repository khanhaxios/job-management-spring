package com.job_manager.mai.service.notification;

import com.job_manager.mai.model.Notification;
import com.job_manager.mai.pusher.NotificationPusher;
import com.job_manager.mai.repository.NotificationRepository;
import com.job_manager.mai.request.notification.CreateNotificationRequest;
import com.job_manager.mai.service.base.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NotificationServiceIpm extends BaseService implements NotificationService {

    private final NotificationPusher notificationPusher;
    private final NotificationRepository notificationRepository;

    @Override
    public void createAndPushNotification(CreateNotificationRequest request) throws Exception {
        Notification notification = getMapper().map(request, Notification.class);
        notificationRepository.saveAndFlush(notification);
        notificationPusher.pushNotification(request);
    }
}

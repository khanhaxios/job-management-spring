package com.job_manager.mai.service.notification;

import com.job_manager.mai.request.notification.CreateNotificationRequest;

public interface NotificationService {
    public void createAndPushNotification(CreateNotificationRequest request) throws Exception;
}

package com.job_manager.mai.contrains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    TYPE_CONTACT(1);
    private final int value;
}

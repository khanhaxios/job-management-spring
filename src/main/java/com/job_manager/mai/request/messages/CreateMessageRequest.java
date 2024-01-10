package com.job_manager.mai.request.messages;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMessageRequest extends MessageRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    private String content;

    private String media;

    @NotBlank
    private String roomId;
}

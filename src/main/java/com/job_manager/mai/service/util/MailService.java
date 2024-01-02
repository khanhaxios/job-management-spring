package com.job_manager.mai.service.util;

import com.job_manager.mai.contrains.Messages;
import com.job_manager.mai.mailer.JavaMail;
import com.job_manager.mai.mailer.SendMailWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMail javaMail;

    public void sendVerifyMail(String to, String code) {
        ThreadPoolService.gI().execute(new SendMailWorker(javaMail, to, Messages.VERIFY_CODE_TITLE, String.format(Messages.VERIFY_MAIL_CONTENT, code)));
    }

}

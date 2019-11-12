package com.micromax.incidencia.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailsender;
    @Async
    public void sendEmail(String to,String subject,String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            log.info("ENVIANDO CORREO A " + to);
            mailsender.send(message);
        }catch(MailSendException e){
         log.error("HUBO UN ERROR ENVIANDO CORREO A " + to);
         log.error(e.getMessage());
        }
    }
}

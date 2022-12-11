package com.selfproject.emailapp.service;


import com.selfproject.emailapp.domain.EmailDetails;
import com.selfproject.emailapp.service.dto.EmailDetailsDto;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailDetailsService {


    EmailDetailsDto sendMail(EmailDetails details);

    EmailDetailsDto sendMailWithAttachment(String recipient, String subject, String body, MultipartFile attachedFile) throws MessagingException, IOException;

}

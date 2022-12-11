package com.selfproject.emailapp.web.rest;

import com.selfproject.emailapp.domain.EmailDetails;
import com.selfproject.emailapp.service.EmailDetailsService;
import com.selfproject.emailapp.service.dto.EmailDetailsDto;
import jakarta.mail.MessagingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final Logger logger = LogManager.getLogger(EmailController.class);

    private final EmailDetailsService emailService;

    public EmailController(EmailDetailsService emailService) {
        this.emailService = emailService;
    }

    // Sending a simple Email
    @PostMapping("/send-mail")
    public ResponseEntity<EmailDetailsDto> sendMail(@RequestBody EmailDetails details) {
        logger.info("Request to send mail with details :{}", details);
        EmailDetailsDto emailDetailsDto = emailService.sendMail(details);
        return ResponseEntity.ok(emailDetailsDto);
    }

    @PostMapping(path = "/send-mail-with-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmailDetailsDto> sendMailWithAttachment(@RequestParam String recipient,
                                                                  @RequestParam String subject,
                                                                  @RequestParam String body,
                                                                  @RequestPart MultipartFile attachedFile) throws MessagingException, IOException {
        logger.info("Request to send mail with subject :{}, body :{} and attachment file  :{}, to :{}", subject, body, attachedFile, recipient);
        EmailDetailsDto emailDetailsDto = emailService.sendMailWithAttachment(recipient, subject, body, attachedFile);
        return ResponseEntity.ok(emailDetailsDto);
    }
}

package com.selfproject.emailapp.service.impl;

import com.selfproject.emailapp.domain.EmailDetails;
import com.selfproject.emailapp.service.EmailDetailsService;
import com.selfproject.emailapp.service.dto.EmailDetailsDto;
import com.selfproject.emailapp.service.mapper.EmailDetailsMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class EmailDetailsServiceImpl implements EmailDetailsService {

    private final JavaMailSender javaMailSender;

    private final EmailDetailsMapper emailDetailsMapper;
    private final Logger logger = LogManager.getLogger(EmailDetailsServiceImpl.class);

    @Value("${spring.mail.username}")
    private String sender;

    public EmailDetailsServiceImpl(JavaMailSender javaMailSender, EmailDetailsMapper emailDetailsMapper) {
        this.javaMailSender = javaMailSender;
        this.emailDetailsMapper = emailDetailsMapper;
    }

    public EmailDetailsDto sendMail(EmailDetails details) {
        logger.debug("Rest to send email with details in service layer :{}", details);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getBody());
        mailMessage.setSubject(details.getSubject());
        javaMailSender.send(mailMessage);
        EmailDetailsDto emaDetailsDto = emailDetailsMapper.toDto(details);
        emaDetailsDto.setSender(sender);
        logger.info("Successfully email sent, details : {}", emaDetailsDto);
        return emaDetailsDto;
    }

    public EmailDetailsDto sendMailWithAttachment(String recipient, String subject, String body, MultipartFile attachedFile) throws MessagingException, IOException {
        logger.debug("Request to send mail with subject :{}, body :{} and attachment file  :{}, to :{} in service layer", subject, body, attachedFile, recipient);
        EmailDetails emailDetails = createNewEmailDetailsObject(recipient, subject, body, attachedFile);
        MimeMessage mimeMessage = generateNewMimeMessage(emailDetails, attachedFile);
        javaMailSender.send(mimeMessage);
        EmailDetailsDto emaDetailsDto = emailDetailsMapper.toDto(emailDetails);
        emaDetailsDto.setSender(sender);
        logger.info("Successfully email sent, details : {}, with attachment :{} ", emaDetailsDto, attachedFile.getOriginalFilename());
        return emaDetailsDto;
    }

    private EmailDetails createNewEmailDetailsObject(String recipient, String subject, String body, MultipartFile attachedFile) throws IOException {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(recipient);
        emailDetails.setSubject(subject);
        emailDetails.setBody(body);
        emailDetails.setAttachment(attachedFile.getBytes());
        return emailDetails;
    }

    private MimeMessage generateNewMimeMessage(EmailDetails details, MultipartFile attachedFile) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getBody());
        mimeMessageHelper.setSubject(details.getSubject());
        File file = new File("src/main/java/com/selfproject/test.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(attachedFile.getBytes());
        os.close();
        mimeMessageHelper.addAttachment(attachedFile.getOriginalFilename(), file);
        return mimeMessage;
    }
}

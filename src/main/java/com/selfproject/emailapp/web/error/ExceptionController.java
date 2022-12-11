package com.selfproject.emailapp.web.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionController {

    private final Logger logger = LogManager.getLogger(ExceptionController.class);

    @Value(value = "${application.error.text.action-failed}")
    private String actionFailed;

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionHandlerClass> handleException(Exception exception) {
        logger.debug("exception throws :{}", exception.getMessage(), exception);
        ExceptionHandlerClass exceptionHandler;

        if (exception instanceof HttpServerErrorException) {
            exceptionHandler = new ExceptionHandlerClass(((HttpServerErrorException) exception).getStatusText(), exception.getMessage(), ((HttpServerErrorException) exception).getStatusCode().value());
        } else if (exception instanceof MailAuthenticationException) {
            exceptionHandler = new ExceptionHandlerClass();
            exceptionHandler.setTitle(exception.getMessage());
            exceptionHandler.setMessage(exception.getCause().getMessage());
            exceptionHandler.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        } else {
            exceptionHandler = new ExceptionHandlerClass();
            exceptionHandler.setTitle(actionFailed);
            exceptionHandler.setMessage(exception.getMessage());
            exceptionHandler.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        logger.error("Action failed, caused by :{}", exceptionHandler);
        return ResponseEntity.status(exceptionHandler.getStatusCode()).body(exceptionHandler);
    }
}

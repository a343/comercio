package com.adr.comercio.infrastructure.handler;

import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.service.adapater.PriceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PriceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(PriceExceptionHandler.class);

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceException ex, WebRequest request) {
        logger.info("Error:: " , ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}


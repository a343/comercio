package com.adr.comercio.application.handler;

import com.adr.comercio.domain.exception.PriceException;
import com.comercio.application.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class PriceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(PriceExceptionHandler.class);

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceException ex, WebRequest request) {
        logger.info("Error:: " , ex);
        if(HttpStatus.NOT_FOUND.equals(ex.getErroCode())){
            return new ResponseEntity<>(ex.getError(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(ex.getError(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
                "Missing required parameter",
                "The parameter '" + ex.getParameterName() + "' is missing"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid parameter type";
        String details = "The parameter '" + ex.getName() + "' should be of type '" + ex.getRequiredType().getSimpleName() + "'";

        if ("applicationDate".equals(ex.getName())) {
            details = "The date format should be 'yyyy-MM-dd'T'HH:mm:ss'";
        }
        ErrorDTO errorDTO = new ErrorDTO(message, details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }


}


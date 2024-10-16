package com.adr.comercio.domain.exception;

import com.comercio.application.dto.ErrorDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class PriceException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private final HttpStatus erroCode;
    private final ErrorDTO error;

    public PriceException(HttpStatus erroCode, ErrorDTO error) {
        this.erroCode = erroCode;
        this.error = error;
    }
}


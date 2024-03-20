package com.ssafy.funding.exception.global;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.*;

import com.ssafy.funding.exception.BadRequestException;
import java.util.Objects;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.warn(e.getMessage(), e);

        final String errMessage =
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(INVALID_REQUEST.getCode(), errMessage));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(
            final BadRequestException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException e, WebRequest request) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(INVALID_REQUEST.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception e) {
        log.error(e.getMessage(), e);

        return ResponseEntity.internalServerError()
                .body(
                        new ExceptionResponse(
                                INTERNAL_SEVER_ERROR.getCode(), INTERNAL_SEVER_ERROR.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(REQUEST_ERROR.getCode(), REQUEST_ERROR.getMessage()));
    }
}

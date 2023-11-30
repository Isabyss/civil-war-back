package com.basementinteractive.civilwar.common.exception;

import com.basementinteractive.civilwar.common.constants.TimeConstants;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("Entity not found: ", e);
        return handleException("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        errorMsg = "Invalid argument(s): " + errorMsg;

        logger.error(errorMsg);
        return handleException(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleInvalidFormulaException(IllegalArgumentException e) {
        logger.error("Invalid argument(s): ", e);
        return handleException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMsg;

        if (ex.getCause() instanceof MismatchedInputException mismatchEx) {
            errorMsg = mismatchEx.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining(", "));
            errorMsg = "Invalid argument for field(s): " + errorMsg;

            logger.error(errorMsg, ex);
            return handleException(errorMsg, HttpStatus.BAD_REQUEST);
        }

        errorMsg = "Invalid input provided.";
        logger.error(errorMsg, ex);

        return handleException(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        logger.error("Internal Server Error: ", e);
        return handleException("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleException(String message, HttpStatus status) {
        ApiException apiException = new ApiException(
                message,
                status.value(),
                ZonedDateTime.now(TimeConstants.DEFAULT_ZONE_ID)
        );

        return new ResponseEntity<>(apiException, status);
    }

}

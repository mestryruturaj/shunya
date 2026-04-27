package io.two.bit.saint.shunya.exception;

import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for handling all application exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle InvalidArgumentException and return 400 Bad Request
     *
     * @param ex the InvalidArgumentException
     * @param request the web request
     * @return ResponseEntity with ErrorDto and 400 status
     */
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorDto> handleInvalidArgumentException(
            InvalidArgumentException ex,
            WebRequest request) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorDto.setMessage(ex.getMessage() != null ? ex.getMessage() : "Invalid argument provided");
        errorDto.setErrorCode("INVALID_ARGUMENT");

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle generic Exception and return 500 Internal Server Error
     *
     * @param ex the exception
     * @param request the web request
     * @return ResponseEntity with ErrorDto and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGlobalException(
            Exception ex,
            WebRequest request) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setMessage("An unexpected error occurred");
        errorDto.setErrorCode("INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


package com.epam.esm.exceptions;

import com.epam.esm.DTO.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Global Exception handler
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(ErrorCodeStatus.NOT_FOUND.getCode());
        errorDTO.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleAlreadyExistsException(AlreadyExistsException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(ErrorCodeStatus.CONFLICT.getCode());
        errorDTO.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<ErrorDTO> handleNotValidException(NotValidException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(ErrorCodeStatus.BAD_REQUEST.getCode());
        errorDTO.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

}

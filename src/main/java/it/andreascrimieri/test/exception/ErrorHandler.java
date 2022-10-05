package it.andreascrimieri.test.exception;

import it.andreascrimieri.test.controller.dto.RestResponse;
import it.andreascrimieri.test.controller.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<RestResponse<UserDTO>> fileException(Exception exception) {
        RestResponse<UserDTO> response = new RestResponse<>(RestResponse.ERROR_CODE, exception.getMessage(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestResponse<UserDTO>> notFoundException(Exception exception) {
        RestResponse<UserDTO> response = new RestResponse<>(RestResponse.SUCCESS_CODE, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }
}


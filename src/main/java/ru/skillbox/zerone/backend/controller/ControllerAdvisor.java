package ru.skillbox.zerone.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skillbox.zerone.backend.exception.UserAlreadyExistException;
import ru.skillbox.zerone.backend.model.dto.response.CommonResponseDTO;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception) {

    var response = CommonResponseDTO.builder()
        .error(exception.getLocalizedMessage())
        .build();

    return ResponseEntity.ok(response);
  }
}
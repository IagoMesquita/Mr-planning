package com.iagomesquita.financialControl.advice;

import com.iagomesquita.financialControl.service.Exception.RequiredParameterException;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
import com.iagomesquita.financialControl.service.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManagerExceptionsController {

  @ExceptionHandler({
      UserNotFoundException.class,
      TransactionNotFount.class
  })
  public ResponseEntity<String> handleNotFoundException(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        exception.getMessage()
    );
  }

  @ExceptionHandler(RequiredParameterException.class)
  public ResponseEntity<String> handleRequiredException(Exception exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        exception.getMessage()
    );
  }
}

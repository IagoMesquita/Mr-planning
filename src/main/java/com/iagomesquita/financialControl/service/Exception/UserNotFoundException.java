package com.iagomesquita.financialControl.service.Exception;

public class UserNotFoundException extends NotFoundExpection {

  public UserNotFoundException() {
    super("Usuário não encontrado!");
  }
}

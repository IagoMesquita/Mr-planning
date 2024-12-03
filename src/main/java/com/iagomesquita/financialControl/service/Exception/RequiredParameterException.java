package com.iagomesquita.financialControl.service.Exception;

public class RequiredParameterException extends  RuntimeException {
  public RequiredParameterException(String menssage) {
    super(menssage);
  }
}

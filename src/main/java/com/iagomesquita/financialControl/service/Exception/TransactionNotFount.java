package com.iagomesquita.financialControl.service.Exception;

public class TransactionNotFount extends NotFoundExpection {

  public TransactionNotFount() {
    super("Transação nao encontrada!");
  }

}

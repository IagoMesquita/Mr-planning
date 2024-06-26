package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import java.time.LocalDate;

public record TransactionDto(Long Id, String title, Double amount, Type type, LocalDate date) {

  public static TransactionDto fromEntity(Transaction transactionDb) {
      return new TransactionDto(
          transactionDb.getId(),
          transactionDb.getTitle(),
          transactionDb.getAmount(),
          transactionDb.getType(),
          transactionDb.getDate()
      );
  }
}

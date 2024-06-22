package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.Transaction;
import java.time.LocalDate;

public record TransactionDto(Long Id, String title, Double amount, String type, LocalDate date) {

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

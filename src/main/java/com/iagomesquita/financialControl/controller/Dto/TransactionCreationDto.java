package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.Transaction;
import java.time.LocalDate;

public record TransactionCreationDto(String title, Double amount, String type, LocalDate date) {
  public Transaction toEntity() {
    return new Transaction(
        title,
        amount,
        type,
        date
    );
  }
}

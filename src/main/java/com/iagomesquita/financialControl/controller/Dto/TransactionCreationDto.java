package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import java.time.LocalDate;

public record TransactionCreationDto(String title, Double amount, Type type) {
  public Transaction toEntity() {
    return new Transaction(
        title,
        amount,
        type
    );
  }
}

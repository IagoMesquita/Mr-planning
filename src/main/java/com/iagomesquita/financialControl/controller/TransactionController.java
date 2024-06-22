package com.iagomesquita.financialControl.controller;

import com.iagomesquita.financialControl.controller.Dto.TransactionCreationDto;
import com.iagomesquita.financialControl.controller.Dto.TransactionDto;
import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<TransactionDto> addTransaction(
      @RequestBody TransactionCreationDto newTransactionCreationDto) {
      Transaction savedTransaction = transactionService.addTransaction(
          newTransactionCreationDto.toEntity()
      );

    return ResponseEntity.status(HttpStatus.CREATED).body(
        TransactionDto.fromEntity(savedTransaction)
    );
  }

  @GetMapping
  public ResponseEntity<List<TransactionDto>> getAllTransactions() {
    List<Transaction> transactionsDb = transactionService.getAllTransactions();
    return ResponseEntity.ok(
        transactionsDb
            .stream()
            .map(TransactionDto::fromEntity)
            .toList()
    );
  }


}

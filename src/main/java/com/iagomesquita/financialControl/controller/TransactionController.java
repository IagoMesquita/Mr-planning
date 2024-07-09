package com.iagomesquita.financialControl.controller;

import com.iagomesquita.financialControl.controller.Dto.TransactionCreationDto;
import com.iagomesquita.financialControl.controller.Dto.TransactionDto;
import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
import com.iagomesquita.financialControl.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
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

//  @GetMapping
//  public ResponseEntity<List<TransactionDto>> getAllTransactions() {
//    List<Transaction> transactionsDb = transactionService.getAllTransactions();
//    return ResponseEntity.ok(
//        transactionsDb
//            .stream()
//            .map(TransactionDto::fromEntity)
//            .toList()
//    );
//  }

  @GetMapping
  public ResponseEntity<List<TransactionDto>> getAllTransactions(
      @RequestParam(required = false) Type type,
      @RequestParam(required = false) Boolean orderByAmount,
      @RequestParam(required = false) Boolean isAmountAsc,
      @RequestParam(required = false) Boolean orderByDate,
      @RequestParam(required = false) Boolean isDateAsc
  ) {
    List<Transaction> transactionsDb = transactionService.filterByTypeAndOrderTransactions(
        type, orderByAmount, isAmountAsc, orderByDate, isDateAsc);

    return ResponseEntity.ok().body(
        transactionsDb.stream()
            .map(TransactionDto::fromEntity)
            .toList()
    );
  }

//  @GetMapping("/{type}")
//  public ResponseEntity<List<TransactionDto>> getAllTransactionsByType(
//      @PathVariable Type type) {
//
//    List<Transaction> transactionsDb = transactionService.getByTypeTransaction(type);
//
//    return ResponseEntity.ok().body(
//        transactionsDb.stream()
//            .map(TransactionDto::fromEntity)
//            .toList()
//    );
//  }
//
//  @GetMapping("/amountDec")
//  public ResponseEntity<List<TransactionDto>> getAllTransactionsByOrderDec() {
//    List<Transaction> transactionDb = transactionService.getAllTransactionByOrderAmountDec();
//
//    return ResponseEntity.ok().body(
//        transactionDb.stream()
//            .map(TransactionDto::fromEntity)
//            .toList()
//    );
//  }
//
//  @GetMapping("/dateDec")
//  public ResponseEntity<List<TransactionDto>> getAllTransactionsByDateDec() {
//    List<Transaction> transactionDb = transactionService.getAllTransactionByOrderDateDesc();
//
//    return ResponseEntity.ok().body(
//        transactionDb.stream()
//            .map(TransactionDto::fromEntity)
//            .toList()
//    );
//  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeTransaction(@PathVariable Long id)
      throws TransactionNotFount {
    String titleTransaction = transactionService.removeTransaction(id);

    return ResponseEntity.ok(titleTransaction);
  }


}

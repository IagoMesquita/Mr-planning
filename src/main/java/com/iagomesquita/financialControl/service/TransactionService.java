package com.iagomesquita.financialControl.service;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Transaction addTransaction(Transaction newTransaction) {

    return transactionRepository.save(newTransaction);
  }

  public List<Transaction> getAllTransactions() {

    return transactionRepository.findAll();
  }


}

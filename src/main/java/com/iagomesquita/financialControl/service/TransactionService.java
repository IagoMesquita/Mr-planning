package com.iagomesquita.financialControl.service;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import com.iagomesquita.financialControl.model.repository.TransactionRepository;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
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

  public List<Transaction> getByTypeTransaction(Type typeTransaction) {
     return transactionRepository.findAllByType(typeTransaction);
  }

  public Transaction getByIdTransaction(Long id) throws TransactionNotFount {
    return transactionRepository.findById(id)
        .orElseThrow(TransactionNotFount::new);
  }

  public String removeTransaction(Long id) throws TransactionNotFount {
    Transaction transactionDb = getByIdTransaction(id);

    transactionRepository.delete(transactionDb);

    return transactionDb.getTitle();
  }

}

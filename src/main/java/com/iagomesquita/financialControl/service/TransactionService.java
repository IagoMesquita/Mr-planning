package com.iagomesquita.financialControl.service;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import com.iagomesquita.financialControl.model.repository.TransactionRepository;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
import com.iagomesquita.financialControl.specification.TransactionSpecification;
import java.util.List;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

  public List<Transaction> findTransactions(
      Type type,
      Boolean orderByAmount, Boolean isAmountAsc,
      Boolean orderByDate, Boolean isDateAsc,
      Integer day, Integer month, Integer year
  ) {

    Specification<Transaction> specification = Specification.where(null);

    if (type != null) {
      specification = specification.and(TransactionSpecification.hasType(type));
    }

    if (orderByAmount != null && orderByAmount) {
      specification = specification.and(
          TransactionSpecification.orderByAmount(isAmountAsc != null ? isAmountAsc : true));
    }

    if (orderByDate != null && orderByDate) {
      specification = specification.and(
          TransactionSpecification.orderByDate(isDateAsc != null ? isDateAsc : true)
      );
    }

    if (day != null) {
      specification = specification.and(
          TransactionSpecification.hasDay(day));
    }

    if (month != null) {
      specification = specification.and(
          TransactionSpecification.hasMonth(month));
    }
    if (year != null) {
      specification = specification.and(
          TransactionSpecification.hasYear(year));
    }
    return transactionRepository.findAll(specification);
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

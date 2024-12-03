package com.iagomesquita.financialControl.service;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.entity.User;
import com.iagomesquita.financialControl.model.enums.Type;
import com.iagomesquita.financialControl.model.repository.TransactionRepository;
import com.iagomesquita.financialControl.model.repository.UserRepository;
import com.iagomesquita.financialControl.service.Exception.RequiredParameterException;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
import com.iagomesquita.financialControl.service.Exception.UserNotFoundException;
import com.iagomesquita.financialControl.specification.TransactionSpecification;
import java.util.List;
import java.util.Objects;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository,
      UserRepository userRepository) {
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }

//  public Transaction addTransaction(Transaction newTransaction) {
//
//    return transactionRepository.save(newTransaction);
//  }

  public List<Transaction> findTransactions(
      Long userId,
      Type type,
      Boolean orderByAmount, Boolean isAmountAsc,
      Boolean orderByDate, Boolean isDateAsc,
      Integer day, Integer month, Integer year
  ) throws UserNotFoundException {
//    if (userId == null) throw new RequiredUserIdException();

    userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

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

    specification = specification.and(
        TransactionSpecification.getByUserId(userId));

    return transactionRepository.findAll(specification);
  }

  public Transaction getByIdTransaction(Long id) throws TransactionNotFount {
    return transactionRepository.findById(id)
        .orElseThrow(TransactionNotFount::new);
  }

//  public String removeTransaction(Long id) throws TransactionNotFount {
//    Transaction transactionDb = getByIdTransaction(id);
//
//    transactionRepository.delete(transactionDb);
//
//    return transactionDb.getTitle();
//  }

  public String removeTransaction(Long userId, Long transactionId)
      throws UserNotFoundException {

    User userDb = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    Transaction transactionDB = userDb.getTransactions()
        .stream()
        .filter(transaction -> Objects.equals(transaction.getId(), transactionId))
        .toList().
        get(0);

    transactionRepository.delete(transactionDB);

    return (transactionDB).getTitle();
  }


  public Transaction addTransactionByUser(Long userId, Transaction newTransaction)
      throws UserNotFoundException {
    User userDb = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

    newTransaction.setUser(userDb);

    return transactionRepository.save(newTransaction);
  }

}

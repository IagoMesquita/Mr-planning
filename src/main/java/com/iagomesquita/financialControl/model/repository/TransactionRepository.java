package com.iagomesquita.financialControl.model.repository;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllByType(Type typeResgistre);
//  List<TransactionDto> findByAmountOrderByAmountDesc(Type amount);
//  List<TransactionDto> findAllByDate(Type amount);
}

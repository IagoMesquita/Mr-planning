package com.iagomesquita.financialControl.model.repository;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>,
    JpaSpecificationExecutor<Transaction> {

//  List<Transaction> findAllByType(Type typeTransaction);
//
//  List<Transaction> findAllByOrderByAmountDesc();
//
//  List<Transaction> findAllByOrderByDateDesc();
}

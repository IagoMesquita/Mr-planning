package com.iagomesquita.financialControl.specification;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {

  public static Specification<Transaction> hasType(Type type) {
    return (transactionRoot, query, criteriaBuilder) -> criteriaBuilder
        .equal(transactionRoot.get("type"), type);
  }

  public static Specification<Transaction> orderByAmount(boolean isAsc) {

    return (transactionRoot, query, criteriaBuilder) -> {
      if (isAsc) {
        query.orderBy(criteriaBuilder.asc(transactionRoot.get("amount")));
      } else {
        query.orderBy(criteriaBuilder.desc(transactionRoot.get("amount")));
      }

      return criteriaBuilder.conjunction();
    };

  }

  public static Specification<Transaction> orderByDate(boolean isAsc) {
    return (transactionRoot, criteriaQuery, criteriaBuilder) -> {
      if (isAsc) {
        criteriaQuery.orderBy(criteriaBuilder.asc(transactionRoot.get("date")));
      } else {
        criteriaQuery.orderBy(criteriaBuilder.desc(transactionRoot.get("date")));
      }

      return criteriaBuilder.conjunction();
    };
  }
}

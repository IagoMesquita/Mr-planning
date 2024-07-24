package com.iagomesquita.financialControl.specification;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import jakarta.persistence.criteria.Predicate;
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

  public static Specification<Transaction> hasDay(int day) {
    return (transactionRoot, query, criteriaBuilder) -> {
      Predicate dayPredicate = criteriaBuilder.equal(
          criteriaBuilder.function("DAY", Integer.class, transactionRoot.get("date")), day);

      return criteriaBuilder.and(dayPredicate);
    };
  }

  public static Specification<Transaction> hasMonth(int month) {
    return (transactionRoot, query, criteriaBuilder) -> {

      Predicate mothPredicate = criteriaBuilder.equal(
          criteriaBuilder.function("MONTH", Integer.class, transactionRoot.get("date")), month);

      return criteriaBuilder.and(mothPredicate);
    };
  }

  public static Specification<Transaction> hasYear(int year) {
    return (transactionRoot, query, criteriaBuilder) -> {

      Predicate yearPredicate = criteriaBuilder.equal(
          criteriaBuilder.function("YEAR", Integer.class, transactionRoot.get("date")), year);

      return criteriaBuilder.and(yearPredicate);
    };
  }

}


/*
criteriaBuilder.function("MONTH", Integer.class, root.get("date"))
cria uma expressão que extrai o mês do campo date da entidade Transaction.
Isso é equivalente a usar a função MONTH do banco de dados para obter o mês de uma data,
por exemplo, MONTH(date).
 */

/*
return criteriaBuilder.and(dayPredicate, mothPredicate, yearPredicate);
Aqui, monthPredicate e yearPredicate são combinados usando criteriaBuilder.and()
para garantir que tanto o mês quanto o ano da data correspondam aos valores fornecidos.
 */
package com.iagomesquita.financialControl.model.entity;

import com.iagomesquita.financialControl.model.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Double amount;
  private Type type;
  private LocalDate date;



  public Transaction(String title, Double amount, String type, LocalDate date) {
  }

  public Transaction(String title, Double amount, Type type, LocalDate date) {
    this.title = title;
    this.amount = amount;
    this.type = type;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getType() {
    return type.getType();
  }

  public void setType(Type type) {
    this.type = type;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}

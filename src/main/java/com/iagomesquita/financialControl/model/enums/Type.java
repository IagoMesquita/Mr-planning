package com.iagomesquita.financialControl.model.enums;

public enum Type {
  INCOME("income"),
  EXPENSE("expense");

  private final String typeName;

  Type(String typeName) {
    this.typeName = typeName;
  }

  public String getType() {
    return this.typeName;
  }


}

package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.User;

public record UserCreateDto(String fullname, String email, String username, String password) {

  public User toEntity() {
    return new User(
        fullname,
        email,
        username,
        password
    );
  }
}

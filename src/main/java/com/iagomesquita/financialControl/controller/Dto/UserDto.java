package com.iagomesquita.financialControl.controller.Dto;

import com.iagomesquita.financialControl.model.entity.User;

public record UserDto(
    Long userId,
    String fullname,
    String email,
    String username,
    String password) {

  public static UserDto fromEntity(User user) {
    return new UserDto(
        user.getId(),
        user.getFullname(),
        user.getEmail(),
        user.getUsername(),
        user.getPassword()
    );
  }
}

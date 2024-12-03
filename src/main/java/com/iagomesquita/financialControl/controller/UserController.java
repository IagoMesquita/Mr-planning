package com.iagomesquita.financialControl.controller;

import com.iagomesquita.financialControl.controller.Dto.UserCreateDto;
import com.iagomesquita.financialControl.controller.Dto.UserDto;
import com.iagomesquita.financialControl.model.entity.User;
import com.iagomesquita.financialControl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@RequestBody UserCreateDto newUser) {
    User userDb = userService.create(newUser.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(
        UserDto.fromEntity(userDb)
    );
  }

}

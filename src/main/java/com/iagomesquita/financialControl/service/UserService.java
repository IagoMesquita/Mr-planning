package com.iagomesquita.financialControl.service;

import com.iagomesquita.financialControl.model.entity.User;
import com.iagomesquita.financialControl.model.repository.UserRepository;
import com.iagomesquita.financialControl.service.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User create(User newUser)  {
    String hashedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());

    newUser.setPassword(hashedPassword);

    return userRepository.save(newUser);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}

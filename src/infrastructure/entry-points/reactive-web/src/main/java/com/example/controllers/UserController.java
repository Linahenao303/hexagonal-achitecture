package com.example.controllers;

import com.example.dtos.users.UserAccountSummaryDTO;
import com.example.dtos.users.UserDTO;
import com.example.handlers.user.CreateUserHandler;
import com.example.handlers.user.GetUserAccountSummaryHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("api/users")
@RestController
public class UserController {

  private final CreateUserHandler createUserHandler;

  private final GetUserAccountSummaryHandler getUserAccountSummaryHandler;

  @PostMapping
  public Mono<ResponseEntity<UserDTO>> createUser(@Valid @RequestBody UserDTO user) {
    return createUserHandler.execute(user).map(ResponseEntity::ok);
  }

  @GetMapping("/{userId}/accounts")
  public Mono<UserAccountSummaryDTO> getUserAccountSummary(@PathVariable Integer userId) {
    return getUserAccountSummaryHandler.getUserAccountSummary(userId);
  }
}

package com.mashup.backend.nawa_invitation_project.user.controller;

import com.mashup.backend.nawa_invitation_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/users/dummy")
  public ResponseEntity<Void> createTestUser() {
    userService.createTestUser();
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

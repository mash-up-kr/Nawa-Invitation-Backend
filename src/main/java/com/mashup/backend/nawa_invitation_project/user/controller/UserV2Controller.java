package com.mashup.backend.nawa_invitation_project.user.controller;

import com.mashup.backend.nawa_invitation_project.user.service.UserV2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserV2Controller {

  private final UserV2Service userV2Service;

  @PostMapping("/apis/v2/users")
  public ResponseEntity<Void> initializeUser(
      @RequestHeader("deviceIdentifier") String deviceIdentifier
  ) {
    userV2Service.initializeUser(deviceIdentifier);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

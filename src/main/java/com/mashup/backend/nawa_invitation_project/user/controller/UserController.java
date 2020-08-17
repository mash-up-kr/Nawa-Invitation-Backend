package com.mashup.backend.nawa_invitation_project.user.controller;

import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import com.mashup.backend.nawa_invitation_project.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저 관련 APIs")
@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @ApiOperation(value = "회원가입 API",
      notes = "첫 화면 접속 시 유저 정보 존재 여부를 확인하는 API")
  @PostMapping("/users")
  public ResponseEntity<Void> checkUserInfo(
      @ApiParam(value = "기기 고유번호", required = true)
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier) {
    userService.findUserOrCreateUserAndInvitations(deviceIdentifier);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

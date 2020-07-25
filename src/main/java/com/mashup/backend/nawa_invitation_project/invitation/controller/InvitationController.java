package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationAddressRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationTimeRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.service.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "초대장 관련 APIs")
@RequiredArgsConstructor
@RestController
public class InvitationController {

  private final InvitationService invitationService;

  @ApiOperation(value = "초대말 수정 API",
      notes = "초대말 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitation/words")
  public ResponseEntity<Void> updateInvitationWords(
      @RequestBody InvitationWordsRequestDto invitationWordsRequestDto
  ) {
    invitationService.updateInvitationWords(invitationWordsRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "날짜/시간 수정 API",
      notes = "날짜/시간 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitation/time")
  public ResponseEntity<Void> updateInvitationTime(
      @RequestBody InvitationTimeRequestDto invitationTimeRequestDto
  ) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "장소 수정 API",
      notes = "장소 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitation/address")
  public ResponseEntity<Void> updateInvitationAddress(
      @RequestBody InvitationAddressRequestDto invitationAddressRequestDto
  ) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

package com.mashup.backend.nawa_invitation_project.invitation.controller;

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
  notes = "초대말 작성하기 완료시 호출되는 API입니다.")
  @PatchMapping("/invitation/words")
  public ResponseEntity<Void> updateInvitationWords(
      @RequestBody InvitationWordsRequestDto invitationWordsRequestDto
  ) {
    invitationService.updateInvitationWords(invitationWordsRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

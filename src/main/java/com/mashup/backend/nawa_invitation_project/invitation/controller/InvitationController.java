package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class InvitationController {

  private final InvitationService invitationService;

  @PatchMapping("/invitation/words")
  public ResponseEntity<Void> updateInvitationWords(
      @RequestBody InvitationWordsRequestDto invitationWordsRequestDto
  ) {
    invitationService.updateInvitationWords(invitationWordsRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

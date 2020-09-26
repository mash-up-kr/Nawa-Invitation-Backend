package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.mashup.backend.nawa_invitation_project.invitation.dto.request.PostInvitationRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.HashCodeResponseDto;
import com.mashup.backend.nawa_invitation_project.invitation.service.InvitationV2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "V2 Invitation Controller (API)")
@RequiredArgsConstructor
@RestController
public class InvitationV2Controller {

  private final InvitationV2Service invitationV2Service;

  @ApiOperation(value = "초대장 등록 API")
  @PostMapping("/apis/v2/invitations")
  public ResponseEntity<HashCodeResponseDto> postInvitation(
      @RequestHeader("deviceIdentifier") String deviceIdentifier,
      PostInvitationRequestDto postInvitationRequestDto,
      @RequestPart("files") MultipartFile[] files
  ) throws IOException {
    return ResponseEntity.status(HttpStatus.OK).body(
        invitationV2Service.postInvitation(deviceIdentifier, postInvitationRequestDto, files));
  }
}

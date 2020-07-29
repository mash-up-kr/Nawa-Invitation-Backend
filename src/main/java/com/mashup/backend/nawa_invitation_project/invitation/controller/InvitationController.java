package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationAddressRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationTimeRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.invitation.service.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "초대장 관련 APIs")
@RequiredArgsConstructor
@RestController
public class InvitationController {

  private final InvitationService invitationService;

  private final InvitationRepository invitationRepository;

  @ApiOperation(value = "초대말 수정 API",
      notes = "초대말 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitation/words")
  public ResponseEntity<Void> updateInvitationWords(
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier,
      @RequestBody InvitationWordsRequestDto invitationWordsRequestDto
  ) {
    invitationService.updateInvitationWords(deviceIdentifier, invitationWordsRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "날짜/시간 수정 API",
      notes = "날짜/시간 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitations/time")
  public ResponseEntity<Void> updateInvitationTime(
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier,
      @RequestBody InvitationTimeRequestDto invitationTimeRequestDto
  ) {
    invitationService.updateInvitationTime(deviceIdentifier, invitationTimeRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "장소 수정 API",
      notes = "장소 입력 완료시 호출되는 API입니다.")
  @PatchMapping("/invitations/address")
  public ResponseEntity<Void> updateInvitationAddress(
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier,
      @RequestBody InvitationAddressRequestDto invitationAddressRequestDto
  ) {
    invitationService.updateInvitationAddress(deviceIdentifier, invitationAddressRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/invitations/{hash-code}")
  public ResponseEntity<ResDetailInvitationDto> getDetailInvitation(
      @PathVariable(value = "hash-code", required = true) String hashCode) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(invitationService.getDetailInvitation(hashCode));
  }

  @PostMapping("/invitations/dummy")
  public ResponseEntity<Void> addInvitationDummyData() {
    invitationRepository.save(Invitation.builder()
        .hashCode("testHashCode")
        .invitationAddressName("testAddress")
        .invitationContents("testContents")
        .invitationPlaceName("testPlaceName")
        .invitationRoadAddressName("testRoadAddressName")
        .invitationTime(LocalDateTime.now())
        .templatesId(1L)
        .x(100D)
        .y(200D)
        .usersId(1L).build());
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

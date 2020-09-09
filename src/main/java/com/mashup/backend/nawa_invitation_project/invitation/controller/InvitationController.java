package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationAddressRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationImageRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationTimeRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.invitation.service.InvitationService;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "초대장 관련 APIs")
@RequiredArgsConstructor
@RestController
public class InvitationController {

  private final InvitationService invitationService;
  private final UserRepository userRepository;
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

  @ApiOperation(value= "사진 등록 API",
    notes = "사진 등록시 사용되는 API입니다. Content-Type을 multipart/form-data로 지정해야합니다.")
  @PostMapping("/invitations/invitation-images")
  public ResponseEntity<Void> uploadInvitationImage(
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier,
      Long templateId,
      MultipartFile file
  ) throws IOException {
    invitationService.uploadInvitationImage(deviceIdentifier, templateId, file);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "초대장 조회 API",
      notes = "hash-code 에 해당하는 완성된 초대장을 조회하는 API입니다.")
  @GetMapping("/invitations/{hash-code}")
  @ApiImplicitParam(name = "hash-code", value = "초대장의 hash-code", required = true,
      dataType = "string", paramType = "path")
  public ResponseEntity<ResDetailInvitationDto> getDetailInvitation(
      @PathVariable(value = "hash-code") String hashCode) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(invitationService.getDetailInvitation(hashCode));
  }

  @GetMapping("/invitations")
  public ResponseEntity<ResDetailInvitationDto> getTemplateSpecificSingleInvitationInfo(
      @RequestHeader(value = "deviceIdentifier") String deviceIdentifier,
      @RequestParam(value = "template-id") Long templateId) {
    if(templateId == null && deviceIdentifier == null){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
    String hashCode = invitationService.getHashCode(deviceIdentifier, templateId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(invitationService.getDetailInvitation(hashCode));
  }

  @ApiIgnore
  @PostMapping("/invitations/dummy")
  @Transactional
  public ResponseEntity<Void> addInvitationDummyData() {
    User testUser = userRepository.findByDeviceIdentifier("test-user")
        .orElseThrow(() -> new NoSuchElementException());

    List<Invitation> invitationsList = invitationRepository.findByUsersId(testUser.getId());
    invitationsList.forEach(invitation -> {
          invitation.updateInvitationWords("[Test]모각코하러 모이자!",
              "[Test]나의 모임에 초대된 감자 친구들! 우리는 엄청난 서비스를 만들 수 있을거야!");
          invitation.updateInvitationTime(LocalDateTime.now());
          invitation.updateInvitationAddress("서울특별시 서초구 서초4동", "서초대로73길 38", "강남역 그레이프라운지", 37.500651D,
              127.024547D);
        }
    );
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

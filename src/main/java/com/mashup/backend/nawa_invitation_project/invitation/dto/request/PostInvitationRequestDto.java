package com.mashup.backend.nawa_invitation_project.invitation.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@RequiredArgsConstructor
public class PostInvitationRequestDto {

  private Long templateId;

  private String invitationTitle;

  private String invitationContents;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime invitationTime;

  private String invitationAddressName;

  private String invitationRoadAddressName;

  private String invitationPlaceName;

  private  Double latitude;

  private Double longitude;

  @Builder
  private PostInvitationRequestDto(
      Long templateId,
      String invitationTitle,
      String invitationContents,
      LocalDateTime invitationTime,
      String invitationAddressName,
      String invitationRoadAddressName,
      String invitationPlaceName,
      Double latitude,
      Double longitude
  ) {
    this.templateId = templateId;
    this.invitationTitle = invitationTitle;
    this.invitationContents = invitationContents;
    this.invitationTime = invitationTime;
    this.invitationAddressName = invitationAddressName;
    this.invitationRoadAddressName = invitationRoadAddressName;
    this.invitationPlaceName = invitationPlaceName;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}

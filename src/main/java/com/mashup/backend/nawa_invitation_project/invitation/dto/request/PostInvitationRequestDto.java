package com.mashup.backend.nawa_invitation_project.invitation.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
public class PostInvitationRequestDto {

  private final Long templateId;

  private final String invitationTitle;

  private final String invitationContents;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private final LocalDateTime invitationTime;

  private final String invitationAddressName;

  private final String invitationRoadAddressName;

  private final String invitationPlaceName;

  private final Double latitude;

  private final Double longitude;

  @Builder
  public PostInvitationRequestDto(
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

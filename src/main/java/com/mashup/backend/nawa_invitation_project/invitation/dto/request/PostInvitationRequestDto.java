package com.mashup.backend.nawa_invitation_project.invitation.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@RequiredArgsConstructor
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
}

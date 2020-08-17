package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResDetailInvitationDto {

  private String templateBackgroundImageUrl;

  private String invitationTitle;

  private String invitationContents;

  private LocalDateTime invitationTime;

  private String invitationPlaceName;

  private MapInfoDto mapInfo;

  @Builder
  private ResDetailInvitationDto(
      String templateBackgroundImageUrl,
      String invitationTitle,
      String invitationContents,
      LocalDateTime invitationTime,
      String invitationPlaceName,
      MapInfoDto mapInfo
  ) {
    this.templateBackgroundImageUrl = templateBackgroundImageUrl;
    this.invitationTitle = invitationTitle;
    this.invitationContents = invitationContents;
    this.invitationTime = invitationTime;
    this.invitationPlaceName = invitationPlaceName;
    this.mapInfo = mapInfo;
  }

}

package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationImageDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResDetailInvitationDto {

  private String templateBackgroundImageUrl;

  private String templateTypeDescription;

  private String invitationTitle;

  private String invitationContents;

  private LocalDateTime invitationTime;

  private String invitationPlaceName;

  private MapInfoDto mapInfo;

  private List<InvitationImageDto> invitationImages;

  @Builder
  private ResDetailInvitationDto(
      String templateBackgroundImageUrl,
      String templateTypeDescription,
      String invitationTitle,
      String invitationContents,
      LocalDateTime invitationTime,
      String invitationPlaceName,
      MapInfoDto mapInfo,
      List<InvitationImageDto> invitationImages
  ) {
    this.templateBackgroundImageUrl = templateBackgroundImageUrl;
    this.templateTypeDescription = templateTypeDescription;
    this.invitationTitle = invitationTitle;
    this.invitationContents = invitationContents;
    this.invitationTime = invitationTime;
    this.invitationPlaceName = invitationPlaceName;
    this.mapInfo = mapInfo;
    this.invitationImages = invitationImages;
  }

}

package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResDetailInvitationDto {

  private String templateImageUrl;

  private String invitationTitle;

  private String invitationContents;

  private String invitationTime;

  private String invitationAddressName;

  private String invitationRoadAddressName;

  private String invitationPlaceName;

  private Double x;

  private Double y;

  @Builder
  private ResDetailInvitationDto(String templateImageUrl,
      String invitationTitle,
      String invitationContents,
      String invitationTime,
      String invitationAddressName,
      String invitationPlaceName,
      Double x,
      Double y) {
    this.templateImageUrl=templateImageUrl;
    this.invitationTitle=invitationTitle;
    this.invitationContents=invitationContents;
    this.invitationTime=invitationTime;
    this.invitationAddressName=invitationAddressName;
    this.invitationRoadAddressName=invitationAddressName;
    this.invitationPlaceName=invitationPlaceName;
    this.x=x;
    this.y=y;
  }

}

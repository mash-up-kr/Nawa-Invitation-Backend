package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MapInfoDto {
  private String invitationAddressName;

  private String invitationRoadAddressName;

  private Double x;

  private Double y;

  @Builder
  private MapInfoDto(String invitationAddressName, String invitationRoadAddressName, Double x, Double y) {
    this.invitationAddressName = invitationAddressName;
    this.invitationRoadAddressName = invitationRoadAddressName;
    this.x = x;
    this.y = y;
  }
}

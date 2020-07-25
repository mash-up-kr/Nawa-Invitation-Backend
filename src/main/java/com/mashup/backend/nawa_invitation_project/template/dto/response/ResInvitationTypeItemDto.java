package com.mashup.backend.nawa_invitation_project.template.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResInvitationTypeItemDto {

  private String typeName;

  private String typeDescription;

  private String imageUrl;

  private Boolean isExistInvitation;

  @Builder
  public ResInvitationTypeItemDto(String typeName, String typeDescription, String imageUrl,
      Boolean isExistInvitation) {
    this.typeName = typeName;
    this.typeDescription = typeDescription;
    this.imageUrl = imageUrl;
    this.isExistInvitation = isExistInvitation;
  }
}

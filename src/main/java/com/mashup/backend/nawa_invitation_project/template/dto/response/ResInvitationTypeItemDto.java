package com.mashup.backend.nawa_invitation_project.template.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResInvitationTypeItemDto {

  private Long templateId;

  private String typeName;

  private String typeDescription;

  private String imageUrl;

  private Boolean isExistInvitation;

  private String invitationHashCode;

  @Builder
  public ResInvitationTypeItemDto(Long templateId, String typeName, String typeDescription,
      String imageUrl,
      Boolean isExistInvitation,
      String invitationHashCode) {
    this.templateId = templateId;
    this.typeName = typeName;
    this.typeDescription = typeDescription;
    this.imageUrl = imageUrl;
    this.isExistInvitation = isExistInvitation;
    this.invitationHashCode = invitationHashCode;
  }
}

package com.mashup.backend.nawa_invitation_project.template.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateTypeInfoDto {
  private Long templateId;

  private String typeName;

  private String typeDescription;

  private String imageUrl;

  private Boolean isExistInvitation;

  private String invitationHashCode;

  private String templateBackgroundImageUrl;

  @Builder
  public TemplateTypeInfoDto(
      Long templateId,
      String typeName,
      String typeDescription,
      String imageUrl,
      Boolean isExistInvitation,
      String invitationHashCode,
      String templateBackgroundImageUrl
  ) {
    this.templateId = templateId;
    this.typeName = typeName;
    this.typeDescription = typeDescription;
    this.imageUrl = imageUrl;
    this.isExistInvitation = isExistInvitation;
    this.invitationHashCode = invitationHashCode;
    this.templateBackgroundImageUrl = templateBackgroundImageUrl;
  }
}

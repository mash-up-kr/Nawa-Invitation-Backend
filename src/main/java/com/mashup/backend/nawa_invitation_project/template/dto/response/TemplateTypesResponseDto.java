package com.mashup.backend.nawa_invitation_project.template.dto.response;

import com.mashup.backend.nawa_invitation_project.template.dto.TemplateTypeInfoDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateTypesResponseDto {

  private List<TemplateTypeInfoDto> invitationTypeItemList;

  @Builder
  public TemplateTypesResponseDto(List<TemplateTypeInfoDto> invitationTypeItemList) {
    this.invitationTypeItemList = invitationTypeItemList;
  }
}
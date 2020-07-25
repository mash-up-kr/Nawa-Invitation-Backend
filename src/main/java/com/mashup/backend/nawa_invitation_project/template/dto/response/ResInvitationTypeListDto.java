package com.mashup.backend.nawa_invitation_project.template.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResInvitationTypeListDto {

  private List<ResInvitationTypeItemDto> invitationTypeItemList;

  @Builder
  public ResInvitationTypeListDto(List<ResInvitationTypeItemDto> invitationTypeItemList) {
    this.invitationTypeItemList = invitationTypeItemList;
  }
}

package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HashCodeResponseDto {
  private String invitationHashCode;

  @Builder
  private HashCodeResponseDto(String invitationHashCode) {
    this.invitationHashCode = invitationHashCode;
  }
}

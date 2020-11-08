package com.mashup.backend.nawa_invitation_project.invitation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashCodeResponseDto {
  private String invitationHashCode;

  @Builder
  private HashCodeResponseDto(String invitationHashCode) {
    this.invitationHashCode = invitationHashCode;
  }
}

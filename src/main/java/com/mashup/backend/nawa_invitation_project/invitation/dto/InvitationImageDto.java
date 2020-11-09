package com.mashup.backend.nawa_invitation_project.invitation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvitationImageDto {

  private Long id;
  private String imageUrl;

  @Builder
  private InvitationImageDto(Long id, String imageUrl) {
    this.id = id;
    this.imageUrl = imageUrl;
  }
}

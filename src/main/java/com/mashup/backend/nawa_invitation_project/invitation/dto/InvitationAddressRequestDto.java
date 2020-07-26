package com.mashup.backend.nawa_invitation_project.invitation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvitationAddressRequestDto {

  private final Long templatesId;
  private final String invitationAddress;
}

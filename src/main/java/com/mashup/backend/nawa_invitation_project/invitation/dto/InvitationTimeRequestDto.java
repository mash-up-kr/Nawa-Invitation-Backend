package com.mashup.backend.nawa_invitation_project.invitation.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvitationTimeRequestDto {
  private final String deviceIdentifier;
  private final Long templatesId;
  private final LocalDateTime invitationTime;
}

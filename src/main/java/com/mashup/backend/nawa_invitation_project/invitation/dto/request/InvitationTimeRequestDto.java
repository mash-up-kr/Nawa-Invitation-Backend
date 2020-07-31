package com.mashup.backend.nawa_invitation_project.invitation.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvitationTimeRequestDto {

  private final Long templatesId;
  private final LocalDateTime invitationTime;
}

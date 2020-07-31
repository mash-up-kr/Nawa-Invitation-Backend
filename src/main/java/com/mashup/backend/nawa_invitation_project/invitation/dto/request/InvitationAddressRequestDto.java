package com.mashup.backend.nawa_invitation_project.invitation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvitationAddressRequestDto {

  private final Long templatesId;
  private final String invitationAddressName;
  private final String invitationRoadAddressName;
  private final String invitationPlaceName;
  private final Double x;
  private final Double y;
}

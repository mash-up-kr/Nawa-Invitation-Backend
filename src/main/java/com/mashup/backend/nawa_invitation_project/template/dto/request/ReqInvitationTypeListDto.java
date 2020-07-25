package com.mashup.backend.nawa_invitation_project.template.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqInvitationTypeListDto {

  @NonNull
  private String deviceIdentifier;
}

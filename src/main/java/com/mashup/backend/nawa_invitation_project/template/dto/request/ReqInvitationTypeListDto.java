package com.mashup.backend.nawa_invitation_project.template.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class ReqInvitationTypeListDto {

  @NonNull
  private String deviceIdentifier;

  @Builder
  private ReqInvitationTypeListDto(String deviceIdentifier){
    this.deviceIdentifier=deviceIdentifier;
  }
}

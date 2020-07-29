package com.mashup.backend.nawa_invitation_project.invitation.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationTimeRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InvitationService {

  private final UserRepository userRepository;
  private final TemplateRepository templateRepository;
  private final InvitationRepository invitationRepository;

  @Transactional
  public void updateInvitationWords(String deviceIdentifier,
      InvitationWordsRequestDto invitationWordsRequestDto) {
    Optional<User> user = userRepository
        .findByDeviceIdentifier(deviceIdentifier);

    List<Invitation> invitation = invitationRepository
        .findByUsersIdAndTemplatesId(
            user.get().getId(),
            invitationWordsRequestDto.getTemplatesId()
        );

    if (invitation.isEmpty()) {
      throw new IllegalArgumentException();
    }

    invitation.get(0).updateInvitationWords(
        invitationWordsRequestDto.getInvitationTitle(),
        invitationWordsRequestDto.getInvitationContents()
    );
  }

  @Transactional
  public void updateInvitationTime(String deviceIdentifier,
      InvitationTimeRequestDto invitationTimeRequestDto) {
    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new IllegalArgumentException("no user"));

    List<Invitation> invitations = invitationRepository
        .findByUsersIdAndTemplatesId(user.getId(), invitationTimeRequestDto.getTemplatesId());

    if (invitations.isEmpty()) {
      throw new IllegalArgumentException();
    }

    invitations.get(0).updateInvitationTime(invitationTimeRequestDto.getInvitationTime());
  }

  public ResDetailInvitationDto getDetailInvitation(String hashCode) {
    Invitation invitation = invitationRepository.findByHashCode(hashCode)
        .orElseThrow(() -> new IllegalArgumentException("no invitation"));

    Long templatesId = invitation.getTemplatesId();
    Template template = templateRepository.findById(templatesId)
        .orElseThrow(() -> new IllegalArgumentException("no template"));

    return ResDetailInvitationDto.builder()
        .invitationAddressName(invitation.getInvitationAddressName())
        .invitationContents(invitation.getInvitationContents())
        .invitationPlaceName(invitation.getInvitationPlaceName())
        .invitationTime(invitation.getInvitationTime())
        .invitationTitle(invitation.getInvitationTitle())
        .templateImageUrl(template.getImageUrl())
        .x(invitation.getX())
        .y(invitation.getY())
        .build();
  }
}

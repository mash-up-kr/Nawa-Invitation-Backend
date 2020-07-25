package com.mashup.backend.nawa_invitation_project.invitation.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
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
  private final InvitationRepository invitationRepository;

  @Transactional
  public void updateInvitationWords(InvitationWordsRequestDto invitationWordsRequestDto) {
    Optional<User> user = userRepository
        .findByDeviceIdentifier(invitationWordsRequestDto.getDeviceIdentifier());

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
}

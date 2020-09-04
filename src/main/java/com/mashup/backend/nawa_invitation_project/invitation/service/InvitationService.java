package com.mashup.backend.nawa_invitation_project.invitation.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationWordsRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationAddressRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.InvitationTimeRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.MapInfoDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
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
    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    Invitation invitation = invitationRepository.findByUsersIdAndTemplatesId(user.getId(),
        invitationWordsRequestDto.getTemplatesId())
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    invitation.updateInvitationWords(
        invitationWordsRequestDto.getInvitationTitle(),
        invitationWordsRequestDto.getInvitationContents()
    );
  }

  @Transactional
  public void updateInvitationTime(String deviceIdentifier,
      InvitationTimeRequestDto invitationTimeRequestDto) {
    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    Invitation invitation = invitationRepository.findByUsersIdAndTemplatesId(user.getId(),
        invitationTimeRequestDto.getTemplatesId())
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    invitation.updateInvitationTime(invitationTimeRequestDto.getInvitationTime());
  }

  @Transactional
  public void updateInvitationAddress(String deviceIdentifier,
      InvitationAddressRequestDto invitationAddressRequestDto) {
    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    Invitation invitation = invitationRepository.findByUsersIdAndTemplatesId(user.getId(),
        invitationAddressRequestDto.getTemplatesId())
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    invitation.updateInvitationAddress(
        invitationAddressRequestDto.getInvitationAddressName(),
        invitationAddressRequestDto.getInvitationRoadAddressName(),
        invitationAddressRequestDto.getInvitationPlaceName(),
        invitationAddressRequestDto.getX(),
        invitationAddressRequestDto.getY()
    );
  }

  public ResDetailInvitationDto getDetailInvitation(String hashCode) {
    Invitation invitation = invitationRepository.findByHashCode(hashCode)
        .orElseThrow(() -> new IllegalArgumentException("no invitation"));

    Long templatesId = invitation.getTemplatesId();
    Template template = templateRepository.findById(templatesId)
        .orElseThrow(() -> new IllegalArgumentException("no template"));

    return ResDetailInvitationDto.builder()
        .templateBackgroundImageUrl(template.getBackgroundImageUrl())
        .templateTypeDescription(template.getTypeDescription())
        .invitationTitle(invitation.getInvitationTitle())
        .invitationContents(invitation.getInvitationContents())
        .invitationTime(invitation.getInvitationTime())
        .invitationPlaceName(invitation.getInvitationPlaceName())
        .mapInfo(MapInfoDto.builder()
            .invitationAddressName(invitation.getInvitationAddressName())
            .invitationRoadAddressName(invitation.getInvitationRoadAddressName())
            .x(invitation.getX())
            .y(invitation.getY())
            .build())
        .build();
  }

  public String getHashCode(String deviceIdentifier, Long templateId) {
    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(()-> new NoSuchElementException());

    if(!templateRepository.existsById(templateId)){
      throw new NoSuchElementException();
    }

    Invitation invitation = invitationRepository.findByUsersIdAndTemplatesId(user.getId(), templateId)
        .orElseThrow(()->new NoSuchElementException());

    return invitation.getHashCode();
  }
}

package com.mashup.backend.nawa_invitation_project.template.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.TemplateTypeInfoDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeItemDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.TemplateTypesResponseDto;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateV2Service {

  private final TemplateRepository templateRepository;
  private final InvitationRepository invitationRepository;
  private final UserRepository userRepository;

  public TemplateTypesResponseDto getTemplateTypeList(
      String deviceIdentifier) {

    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없음"));

    List<TemplateTypeInfoDto> list = templateRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(Template::getId))
        .map(template -> TemplateTypeInfoDto.builder()
            .templateId(template.getId())
            .typeName(template.getTypeName())
            .typeDescription(template.getTypeDescription())
            .imageUrl(template.getImageUrl())
            .isExistInvitation(isExistUsersTemplateInvitation(user.getId(), template.getId()))
            .invitationHashCode(getRecentInvitationHashCode(template.getId(), user.getId()))
            .build())
        .collect(Collectors.toList());

    return TemplateTypesResponseDto.builder()
        .invitationTypeItemList(list).build();
  }

  private String getRecentInvitationHashCode(Long templatesId, Long usersId) {
    List<Invitation> invitations = invitationRepository
        .findAllByUsersIdAndTemplatesIdOrderByCreatedAtDesc(usersId, templatesId);
    if (invitations.size() == 0) {
      return null;
    }
    return invitations.get(0).getHashCode();
  }

  private Boolean isExistUsersTemplateInvitation(Long usersId, Long templatesId) {
    Optional<Invitation> invitation = invitationRepository
        .findByUsersIdAndTemplatesId(usersId, templatesId);
    if (!invitation.isPresent()) {
      return false;
    }
    return !isNullForAllContents(invitation.get());
  }

  private Boolean isNullForAllContents(Invitation invitation) {
    return invitation.getInvitationAddressName() == null
        && invitation.getInvitationContents() == null
        && invitation.getInvitationPlaceName() == null
        && invitation.getInvitationRoadAddressName() == null
        && invitation.getInvitationTime() == null
        && invitation.getInvitationTitle() == null
        && invitation.getX() == null
        && invitation.getY() == null;
  }
}

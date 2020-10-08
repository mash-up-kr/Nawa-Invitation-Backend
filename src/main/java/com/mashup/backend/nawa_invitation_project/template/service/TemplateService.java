package com.mashup.backend.nawa_invitation_project.template.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeItemDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TemplateService {

  private final TemplateRepository templateRepository;
  private final InvitationRepository invitationRepository;
  private final UserRepository userRepository;

  public ResInvitationTypeListDto getTemplateTypeList(
      String deviceIdentifier) {

    User user = userRepository.findByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없음")); //TODO : custom exception

    List<ResInvitationTypeItemDto> list = templateRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(Template::getId))
        .map(template -> ResInvitationTypeItemDto.builder()
            .templateId(template.getId())
            .typeName(template.getTypeName())
            .typeDescription(template.getTypeDescription())
            .imageUrl(template.getImageUrl())
            .isExistInvitation(isExistUsersTemplateInvitation(user.getId(), template.getId()))
            .invitationHashCode(getInvitationHashCode(template.getId(),user.getId()))
            .build())
        .collect(Collectors.toList());

    return ResInvitationTypeListDto.builder()
        .invitationTypeItemList(list).build();
  }

  private String getInvitationHashCode(Long templatesId, Long usersId) {
    Invitation invitation = invitationRepository.findTopByUsersIdAndTemplatesIdOrderByIdDesc(usersId, templatesId)
        .orElseThrow(()->new NoSuchElementException());

    return invitation.getHashCode();
  }

  private Boolean isExistUsersTemplateInvitation(Long usersId, Long templatesId) {
    Invitation invitation = invitationRepository.findTopByUsersIdAndTemplatesIdOrderByIdDesc(usersId, templatesId)
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    return !isNullForAllContents(invitation);
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

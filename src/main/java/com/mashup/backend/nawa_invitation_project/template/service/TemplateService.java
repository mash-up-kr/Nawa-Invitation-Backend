package com.mashup.backend.nawa_invitation_project.template.service;

import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.request.ReqInvitationTypeListDto;
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

  public ResInvitationTypeListDto getInvitationTypeList(
      ReqInvitationTypeListDto reqInvitationTypeListDto) {

    String deviceIdentifier = reqInvitationTypeListDto.getDeviceIdentifier();
    User user = userRepository.findUserByDeviceIdentifier(deviceIdentifier)
        .orElseThrow(() -> new NoSuchElementException()); //TODO : custom exception

    List<ResInvitationTypeItemDto> list = templateRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(Template::getId))
        .map(template -> ResInvitationTypeItemDto.builder()
            .typeName(template.getTypeName())
            .typeDescription(template.getTypeDescription())
            .imageUrl(template.getImageUrl())
            .isExistInvitation(
                invitationRepository.existsByTemplatesIdAndUsersId(template.getId(), user.getId()))
            .build())
        .collect(Collectors.toList());

    return ResInvitationTypeListDto.builder()
        .invitationTypeItemList(list).build();
  }
}

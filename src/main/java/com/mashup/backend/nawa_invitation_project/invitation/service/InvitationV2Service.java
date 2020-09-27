package com.mashup.backend.nawa_invitation_project.invitation.service;

import com.mashup.backend.nawa_invitation_project.common.aws.AwsS3Service;
import com.mashup.backend.nawa_invitation_project.common.customUtil.CustomUtil;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationImage;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationImageRepository;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.InvitationImageDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.PostInvitationRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.HashCodeResponseDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.MapInfoDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class InvitationV2Service {

  private final UserRepository userRepository;
  private final InvitationRepository invitationRepository;
  private final InvitationImageRepository invitationImageRepository;
  private final TemplateRepository templateRepository;
  private final AwsS3Service awsS3Service;

  @Transactional
  public HashCodeResponseDto postInvitation(
      String deviceIdentifier,
      PostInvitationRequestDto postInvitationRequestDto,
      MultipartFile[] files
  ) throws IOException {
    Optional<User> user = userRepository.findByDeviceIdentifier(deviceIdentifier);

    Invitation invitation = Invitation.builder()
        .invitationTitle(postInvitationRequestDto.getInvitationTitle())
        .invitationContents(postInvitationRequestDto.getInvitationContents())
        .invitationTime(postInvitationRequestDto.getInvitationTime())
        .invitationAddressName(postInvitationRequestDto.getInvitationAddressName())
        .invitationRoadAddressName(postInvitationRequestDto.getInvitationRoadAddressName())
        .invitationPlaceName(postInvitationRequestDto.getInvitationPlaceName())
        .x(postInvitationRequestDto.getLongitude())
        .y(postInvitationRequestDto.getLatitude())
        .hashCode(CustomUtil.getBase64Uuid())
        .usersId(user.get().getId())
        .templatesId(postInvitationRequestDto.getTemplateId())
        .build();

    invitationRepository.save(invitation);

    ArrayList<InvitationImage> invitationImageEntities = new ArrayList<InvitationImage>();
    for (MultipartFile file : files) {
      String imageUrl = awsS3Service.upload(file);
      InvitationImage invitationImage = InvitationImage.builder()
          .imageUrl(imageUrl)
          .invitationId(invitation.getId())
          .build();
      invitationImageEntities.add(invitationImage);
    }
    invitationImageRepository.saveAll(invitationImageEntities);

    return HashCodeResponseDto.builder().invitationHashCode(invitation.getHashCode()).build();
  }

  public ResDetailInvitationDto getInvitationInfo(String hashCode) {
    Invitation invitation = invitationRepository.findByHashCode(hashCode)
        .orElseThrow(() -> new IllegalArgumentException("no invitation"));

    Long templatesId = invitation.getTemplatesId();
    Template template = templateRepository.findById(templatesId)
        .orElseThrow(() -> new IllegalArgumentException("no template"));

    MapInfoDto mapInfo = null;
    if (!isCustomPlace(invitation.getX(), invitation.getY())) {
      mapInfo = MapInfoDto.builder()
          .invitationAddressName(invitation.getInvitationAddressName())
          .invitationRoadAddressName(invitation.getInvitationRoadAddressName())
          .x(invitation.getX())
          .y(invitation.getY())
          .build();
    }

    List<InvitationImage> invitationImages = invitationImageRepository
        .findAllByInvitationId(invitation.getId());
    List<InvitationImageDto> invitationImageDtos = new ArrayList<InvitationImageDto>();
    for (InvitationImage invitationImage : invitationImages) {
      InvitationImageDto invitationImageDto = InvitationImageDto.builder()
          .id(invitationImage.getId())
          .imageUrl(invitationImage.getImageUrl())
          .build();
      invitationImageDtos.add(invitationImageDto);
    }

    return ResDetailInvitationDto.builder()
        .templateBackgroundImageUrl(template.getBackgroundImageUrl())
        .templateTypeDescription(template.getTypeDescription())
        .invitationTitle(invitation.getInvitationTitle())
        .invitationContents(invitation.getInvitationContents())
        .invitationTime(invitation.getInvitationTime())
        .invitationPlaceName(invitation.getInvitationPlaceName())
        .mapInfo(mapInfo)
        .invitationImages(invitationImageDtos)
        .build();
  }

  private boolean isCustomPlace(Double x, Double y) {
    return !(x != null && y != null);
  }
}

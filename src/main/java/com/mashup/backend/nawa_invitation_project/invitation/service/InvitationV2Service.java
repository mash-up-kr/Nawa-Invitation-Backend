package com.mashup.backend.nawa_invitation_project.invitation.service;

import com.mashup.backend.nawa_invitation_project.common.aws.AwsS3Service;
import com.mashup.backend.nawa_invitation_project.common.customUtil.CustomUtil;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationImage;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationImageRepository;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.PostInvitationRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.HashCodeResponseDto;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
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
}

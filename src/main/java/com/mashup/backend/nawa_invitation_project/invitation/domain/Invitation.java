package com.mashup.backend.nawa_invitation_project.invitation.domain;

import com.mashup.backend.nawa_invitation_project.common.domain.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "invitations")
@Entity
@SQLDelete(sql = "UPDATE invitations SET removed_at=NOW() WHERE id =?")
@Where(clause = "removed_at is null")
public class Invitation extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String invitationTitle;

  private String invitationContents;

  private LocalDateTime invitationTime;

  private String invitationAddressName;

  private String invitationRoadAddressName;

  private String invitationPlaceName;

  private Double x;

  private Double y;

  private String hashCode;

  private Long usersId;

  private Long templatesId;

  @Builder
  private Invitation(
      String invitationContents,
      LocalDateTime invitationTime,
      String invitationAddressName,
      String invitationRoadAddressName,
      String invitationPlaceName,
      Double x,
      Double y,
      String hashCode,
      Long usersId,
      Long templatesId
  ) {
    this.invitationContents = invitationContents;
    this.invitationTime = invitationTime;
    this.invitationAddressName = invitationAddressName;
    this.invitationRoadAddressName = invitationRoadAddressName;
    this.invitationPlaceName = invitationPlaceName;
    this.x = x;
    this.y = y;
    this.hashCode = hashCode;
    this.usersId = usersId;
    this.templatesId = templatesId;
  }

  public void updateInvitationWords(String invitationTitle, String invitationContents) {
    this.invitationTitle = invitationTitle;
    this.invitationContents = invitationContents;
  }

  public void updateHashCode(String hashCode) {
    this.hashCode = hashCode;
  }

  public void updateInvitationTime(LocalDateTime invitationTime) {
    this.invitationTime = invitationTime;
  }
}

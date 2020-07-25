package com.mashup.backend.nawa_invitation_project.invitation.domain;

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
@Where(clause = "removed_at = null")
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String invitationContents;

  private String invitationTime;

  private String invitationAddress;

  private String invitationLatitude;

  private String invitationLongitude;

  private String hashedCode;

  private Long usersId;

  private Long templatesId;

  @Builder
  private Invitation(
      String invitationContents,
      String invitationTime,
      String invitationAddress,
      String invitationLatitude,
      String invitationLongitude,
      String hashedCode,
      Long usersId,
      Long templatesId
  ) {
    this.invitationContents = invitationContents;
    this.invitationTime = invitationTime;
    this.invitationAddress = invitationAddress;
    this.invitationLatitude = invitationLatitude;
    this.invitationLongitude = invitationLongitude;
    this.hashedCode = hashedCode;
    this.usersId = usersId;
    this.templatesId = templatesId;
  }
}
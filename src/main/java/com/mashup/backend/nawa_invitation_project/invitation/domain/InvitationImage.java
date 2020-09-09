package com.mashup.backend.nawa_invitation_project.invitation.domain;

import com.mashup.backend.nawa_invitation_project.common.domain.BaseTimeEntity;
import javax.persistence.Column;
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
@Table(name = "invitation_images")
@Entity
@SQLDelete(sql = "UPDATE invitation_images SET removed_at=NOW() WHERE id =?")
@Where(clause = "removed_at is null")
public class InvitationImage extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 1000)
  private String imageUrl;

  private Long invitationId;

  @Builder
  private InvitationImage(String imageUrl, Long invitationId) {
    this.imageUrl = imageUrl;
    this.invitationId = invitationId;
  }
}

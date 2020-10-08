package com.mashup.backend.nawa_invitation_project.comment.domain;

import com.mashup.backend.nawa_invitation_project.common.domain.BaseTimeEntity;
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
@Table(name = "comments")
@Entity
@SQLDelete(sql = "UPDATE comment SET removed_at=NOW() WHERE id =?")
@Where(clause = "removed_at is null")
public class Comment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userName;

  private String content;

  private Long invitationsId;

  @Builder
  private Comment(
      String userName,
      String content,
      Long invitationsId
  ) {
    this.userName = userName;
    this.content = content;
    this.invitationsId = invitationsId;
  }
}

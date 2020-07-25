package com.mashup.backend.nawa_invitation_project.user.domain;

import com.mashup.backend.nawa_invitation_project.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
@SQLDelete(sql = "UPDATE users SET removed_at=NOW() WHERE id=?")
@Where(clause = "removed_at is null")
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String deviceIdentifier;

  private String nickName;

  @Builder
  private User(String deviceIdentifier, String nickName) {
    this.deviceIdentifier = deviceIdentifier;
    this.nickName = nickName;
  }
}

package com.mashup.backend.nawa_invitation_project.template.domain;

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
@Table(name = "templates")
@Entity
@SQLDelete(sql = "UPDATE templates SET removed_at=NOW() WHERE id=?")
@Where(clause = "removed_at = null")
public class Template extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String typeName;

  private String typeDescription;

  private String imageUrl;

  @Builder
  private Template(String typeName, String typeDescription, String imageUrl) {
    this.typeName = typeName;
    this.typeDescription = typeDescription;
    this.imageUrl = imageUrl;
  }
}

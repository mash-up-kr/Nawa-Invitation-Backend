package com.mashup.backend.nawa_invitation_project.comment.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

  private Long id;

  private String userName;

  private String content;

  private LocalDateTime createdAt;

  @Builder
  public CommentResponseDto(
      Long id,
      String userName,
      String content,
      LocalDateTime createdAt
  ) {
    this.id = id;
    this.userName = userName;
    this.content = content;
    this.createdAt = createdAt;
  }
}

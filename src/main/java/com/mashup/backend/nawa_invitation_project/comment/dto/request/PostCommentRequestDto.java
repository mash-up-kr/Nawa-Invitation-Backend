package com.mashup.backend.nawa_invitation_project.comment.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentRequestDto {

  private String userName;
  private String content;

  @Builder
  public PostCommentRequestDto(String userName, String content) {
    this.userName = userName;
    this.content = content;
  }
}

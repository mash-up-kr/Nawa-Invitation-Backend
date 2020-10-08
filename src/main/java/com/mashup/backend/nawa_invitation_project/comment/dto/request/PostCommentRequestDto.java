package com.mashup.backend.nawa_invitation_project.comment.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCommentRequestDto {

  private final String userName;
  private final String content;
}

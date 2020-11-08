package com.mashup.backend.nawa_invitation_project.comment.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetCommentResponseDto {

  List<CommentResponseDto> comments;

  @Builder
  public GetCommentResponseDto(List<CommentResponseDto> comments) {
    this.comments = comments;
  }
}

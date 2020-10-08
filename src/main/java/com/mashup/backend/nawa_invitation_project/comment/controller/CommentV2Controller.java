package com.mashup.backend.nawa_invitation_project.comment.controller;

import com.mashup.backend.nawa_invitation_project.comment.dto.request.PostCommentRequestDto;
import com.mashup.backend.nawa_invitation_project.comment.service.CommentV2Service;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentV2Controller {

  private final CommentV2Service commentV2Service;

  @ApiOperation(value = "{hashcode}에 대해당하는 초대장에 댓글을 추가하는 API")
  @PostMapping("/apis/v2/invitations/{hashcode}/comments")
  public ResponseEntity<Void> postComment(
      @PathVariable(value = "hashcode") String hashcode,
      @RequestBody PostCommentRequestDto postCommentRequestDto
  ) {
    commentV2Service.postComment(hashcode, postCommentRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

package com.mashup.backend.nawa_invitation_project.comment.service;

import com.mashup.backend.nawa_invitation_project.comment.domain.Comment;
import com.mashup.backend.nawa_invitation_project.comment.domain.CommentRepository;
import com.mashup.backend.nawa_invitation_project.comment.dto.request.PostCommentRequestDto;
import com.mashup.backend.nawa_invitation_project.comment.dto.response.CommentResponseDto;
import com.mashup.backend.nawa_invitation_project.comment.dto.response.GetCommentResponseDto;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentV2Service {

  private final InvitationRepository invitationRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public GetCommentResponseDto getComment(String hashcode) {
    Invitation invitation = invitationRepository.findByHashCode(hashcode)
        .orElseThrow(() -> new IllegalArgumentException("no invitations"));

    List<Comment> comments = commentRepository
        .findAllByInvitationsIdOrderByCreatedAtDesc(invitation.getId());

    List<CommentResponseDto> commentResponseDtos = new ArrayList<CommentResponseDto>();

    for (Comment comment : comments) {
      commentResponseDtos.add(
          CommentResponseDto.builder()
              .id(comment.getId())
              .userName(comment.getUserName())
              .content(comment.getContent())
              .createdAt(comment.getCreatedAt())
              .build()
      );
    }

    return GetCommentResponseDto.builder().comments(commentResponseDtos).build();
  }

  @Transactional
  public void postComment(String hashcode, PostCommentRequestDto postCommentRequestDto) {
    Invitation invitation = invitationRepository.findByHashCode(hashcode)
        .orElseThrow(() -> new IllegalArgumentException("no invitations"));

    Comment comment = Comment.builder()
        .content(postCommentRequestDto.getContent())
        .userName(postCommentRequestDto.getUserName())
        .invitationsId(invitation.getId())
        .build();

    commentRepository.save(comment);
  }
}

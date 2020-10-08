package com.mashup.backend.nawa_invitation_project.comment.service;

import com.mashup.backend.nawa_invitation_project.comment.domain.Comment;
import com.mashup.backend.nawa_invitation_project.comment.domain.CommentRepository;
import com.mashup.backend.nawa_invitation_project.comment.dto.request.PostCommentRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentV2Service {

  private final InvitationRepository invitationRepository;
  private final CommentRepository commentRepository;

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

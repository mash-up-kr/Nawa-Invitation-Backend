package com.mashup.backend.nawa_invitation_project.comment.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByInvitationsIdOrderByCreatedAtDesc(Long invitationsId);
}

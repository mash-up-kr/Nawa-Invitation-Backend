package com.mashup.backend.nawa_invitation_project.invitation.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  Boolean existsByTemplatesIdAndUsersId(Long templateId, Long userId);

  Optional<Invitation> findByUsersIdAndTemplatesId(Long usersId, Long templatesId);

  List<Invitation> findAllByUsersIdAndTemplatesIdOrderByCreatedAtDesc(Long usersId, Long templatesId);

  Optional<Invitation> findByHashCode(String hashCode);

  List<Invitation> findByUsersId(Long usersId);
}

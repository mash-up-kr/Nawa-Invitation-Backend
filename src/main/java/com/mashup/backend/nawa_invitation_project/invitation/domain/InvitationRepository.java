package com.mashup.backend.nawa_invitation_project.invitation.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  Boolean existsByTemplatesIdAndUsersId(Long templateId, Long userId);

  List<Invitation> findByUsersIdAndTemplatesId(Long usersId, Long templatesId);
}

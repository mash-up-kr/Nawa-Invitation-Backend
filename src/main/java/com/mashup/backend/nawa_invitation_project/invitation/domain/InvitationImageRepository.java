package com.mashup.backend.nawa_invitation_project.invitation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationImageRepository extends JpaRepository<InvitationImage, Long> {

}

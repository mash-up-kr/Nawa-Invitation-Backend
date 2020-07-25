package com.mashup.backend.nawa_invitation_project.user.service;

import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private UserRepository userRepository;

  public void createTestUser() {
    userRepository.save(User.builder().deviceIdentifier("1111").nickName("testName").build());
  }
}

package com.mashup.backend.nawa_invitation_project.user.service;

import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserV2Service {

  private final UserRepository userRepository;

  public void initializeUser(String deviceIdentifier) {
    Optional<User> user = userRepository.findByDeviceIdentifier(deviceIdentifier);
    if (user.isPresent()) {
      return;
    }
    userRepository.save(User.builder().deviceIdentifier(deviceIdentifier).build());
  }
}

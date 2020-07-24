package com.mashup.backend.nawa_invitation_project.user;

import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void 사용자_생성_테스트(){
        User createdUser = userRepository.save(User.builder()
                .deviceIdentifier("123")
                .nickName("닉네임")
                .build());

        assertThat(createdUser.getId(), is(1L));
        assertThat(createdUser.getDeviceIdentifier(), is("123"));
    }

    @Test
    public void 사용자_소프트삭제_테스트_without_where_annotation(){
        User savedUser = userRepository.save(User.builder()
                                                    .deviceIdentifier("123")
                                                    .nickName("닉네임")
                                                    .build());
        assertNull(savedUser.getRemovedAt()); //removed_at=null 일때 삭제되지 않은 사용자

        userRepository.delete(savedUser);

        User deletedUser = userRepository.findById(1L)
                .orElseThrow(()-> new IllegalArgumentException("no such user"));

        assertThat(deletedUser.getRemovedAt().isBefore(LocalDateTime.now()), is(true));
    }

    @Test
    public void 사용자_소프트삭제_테스트_with_where_annotation(){
        User savedUser = userRepository.save(User.builder()
                .deviceIdentifier("123")
                .nickName("닉네임")
                .build());

        userRepository.delete(savedUser);
        Optional<User> deletedUser = userRepository.findById(1L);

        assertThat(deletedUser.isPresent(), is(false));
    }
}

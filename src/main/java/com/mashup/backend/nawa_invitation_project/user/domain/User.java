package com.mashup.backend.nawa_invitation_project.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceIdentifier;

    private String nickName;

    @Builder
    private User(String deviceIdentifier, String nickName){
        this.deviceIdentifier=deviceIdentifier;
        this.nickName=nickName;
    }
}

package com.mashup.backend.nawa_invitation_project.template.controller;

import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@RestController
public class TemplateController {

  private final TemplateService templateService;

  private final TemplateRepository templateRepository;

  @GetMapping("/template-types-list")
  public ResponseEntity<ResInvitationTypeListDto> getTemplateTypeList(
      @RequestHeader("deviceIdentifier") String deviceIdentifier) {

    if (deviceIdentifier == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(templateService.getTemplateTypeList(deviceIdentifier));
  }

  @ApiIgnore
  @PostMapping("/template-types/dummy")
  public ResponseEntity<Void> addTemplateTypesData() {
    templateRepository.save(Template.builder()
        .typeName("만수르형")
        .typeDescription("내가 쏠테니까\n"
            + "넌 오기만 해!! \uD83E\uDD11")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/money.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/money.png")
        .build());

    templateRepository.save(Template.builder()
        .typeName("마이웨이형")
        .typeDescription("너가 오던지 말던지\n"
            + "난 무조건 간다 \uD83D\uDE0F")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/myway.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/myway.png")
        .build());

    templateRepository.save(Template.builder()
        .typeName("제발형")
        .typeDescription("제발ㅠㅠ한 명이라도\n"
            + "꼭 와줘야해...\uD83D\uDE22")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/please.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/please.png")
        .build());

    templateRepository.save(Template.builder()
        .typeName("협박형")
        .typeDescription("☠️안오면 죽음 뿐!☠️\n"
            + "알겠냐?")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/threat.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/threat.png")
        .build());

    templateRepository.save(Template.builder()
        .typeName("애교형")
        .typeDescription("난 너가 꼭 와주면 좋겠어\uD83D\uDC96\n"
            + "와줄꺼지?")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/charm.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/charm.png")
        .build());

    templateRepository.save(Template.builder()
        .typeName("형님형")
        .typeDescription("마! 엉아다~\n"
            + "집합해라~\uD83D\uDE0E")
        .imageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-no-background/elder.png")
        .backgroundImageUrl("https://nawa-invitation.s3.ap-northeast-2.amazonaws.com/template-character-with-background/elder.png")
        .build());

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

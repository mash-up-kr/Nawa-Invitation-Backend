package com.mashup.backend.nawa_invitation_project.template.controller;

import com.mashup.backend.nawa_invitation_project.template.dto.request.ReqInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.template.service.TemplateService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@RestController
public class TemplateController {

  private final TemplateService templateService;

  @GetMapping("/invitation-types-list")
  public ResponseEntity<ResInvitationTypeListDto> getInvitationTypeList(
      @RequestBody @Valid ReqInvitationTypeListDto reqInvitationTypeListDto,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(templateService.getInvitationTypeList(reqInvitationTypeListDto));
  }
}

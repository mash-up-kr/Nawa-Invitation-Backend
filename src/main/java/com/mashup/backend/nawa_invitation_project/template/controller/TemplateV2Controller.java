package com.mashup.backend.nawa_invitation_project.template.controller;

import com.mashup.backend.nawa_invitation_project.template.dto.response.TemplateTypesResponseDto;
import com.mashup.backend.nawa_invitation_project.template.service.TemplateV2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TemplateV2Controller {

  private final TemplateV2Service templateV2Service;

  @GetMapping("/apis/v2/template-types")
  public ResponseEntity<TemplateTypesResponseDto> getTemplateTypeList(
      @RequestHeader("deviceIdentifier") String deviceIdentifier
  ) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(templateV2Service.getTemplateTypeList(deviceIdentifier));
  }
}

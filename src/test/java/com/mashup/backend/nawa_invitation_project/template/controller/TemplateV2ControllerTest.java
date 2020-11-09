package com.mashup.backend.nawa_invitation_project.template.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.TemplateTypeInfoDto;
import com.mashup.backend.nawa_invitation_project.template.dto.response.TemplateTypesResponseDto;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TemplateV2ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private TemplateRepository templateRepository;

  @Autowired
  private UserRepository userRepository;

  private String deviceIdentifier;

  private String deviceIdentifierHeaderName;

  @Before
  public void setUp() throws Exception {
    this.deviceIdentifier = "abc";
    this.deviceIdentifierHeaderName = "deviceIdentifier";

    userRepository.save(User.builder()
        .deviceIdentifier(this.deviceIdentifier)
        .build());

    mvc.perform(post("/template-types/dummy"))
        .andExpect(status().isOk());
  }

  @After
  public void tearDown() {
    templateRepository.deleteAll();
  }

  @Test
  public void 유형목록을_받는다() throws Exception {
    String getTemplateTypesUrl = "/apis/v2/template-types";
    MvcResult mvcResult = mvc.perform(get(getTemplateTypesUrl)
        .header(this.deviceIdentifierHeaderName, this.deviceIdentifier))
        .andExpect(status().isOk())
        .andReturn();
    ObjectMapper mapper = new ObjectMapper();
    TemplateTypesResponseDto templateTypesResponseDto = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), TemplateTypesResponseDto.class);
    List<TemplateTypeInfoDto> invitationTypeItemList = templateTypesResponseDto
        .getInvitationTypeItemList();
    assertThat(invitationTypeItemList.size()).isEqualTo(6);

    mvc.perform(get(getTemplateTypesUrl))
        .andExpect(status().is4xxClientError());
  }
}

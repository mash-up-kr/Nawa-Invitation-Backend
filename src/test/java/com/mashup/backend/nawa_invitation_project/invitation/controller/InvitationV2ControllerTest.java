package com.mashup.backend.nawa_invitation_project.invitation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.PostInvitationRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.HashCodeResponseDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.ResDetailInvitationDto;
import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class InvitationV2ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InvitationRepository invitationRepository;

  @Autowired
  private TemplateRepository templateRepository;

  private String deviceIdentifier;
  private String deviceIdentifierHeaderName;

  @Before
  public void setUp() throws Exception {
    mvc.perform(post("/template-types/dummy"))
        .andExpect(status().isOk());

    this.deviceIdentifier = "abc";
    this.deviceIdentifierHeaderName = "deviceIdentifier";
    userRepository.save(User.builder()
        .deviceIdentifier(deviceIdentifier)
        .build());
  }

  @After
  public void tearDown() {
    invitationRepository.deleteAll();
    userRepository.deleteAll();
    templateRepository.deleteAll();
  }

  @Test
  public void 초대장이_등록된다() throws Exception {
    Long templateId = 1L;
    String invitationTitle = "invitationTitle";
    String invitationContents = "invitationContents";
    LocalDateTime invitationTime = LocalDateTime.now();
    String invitationAddressName = "addressName";
    String invitationRoadAddressName = "roadAddressName";
    String invitationPlaceName = "placeName";
    Double latitude = 5.5D;
    Double longitude = 5.6D;
    PostInvitationRequestDto postInvitationRequestDto = PostInvitationRequestDto.builder()
        .templateId(templateId)
        .invitationTitle(invitationTitle)
        .invitationContents(invitationContents)
        .invitationTime(invitationTime)
        .invitationAddressName(invitationAddressName)
        .invitationRoadAddressName(invitationRoadAddressName)
        .invitationPlaceName(invitationPlaceName)
        .latitude(latitude)
        .longitude(longitude)
        .build();

    String invitationPostUrl = "/apis/v2/invitations";

    mvc.perform(post(invitationPostUrl)
        .header(deviceIdentifierHeaderName, deviceIdentifier)
        .content(new ObjectMapper().writeValueAsString(postInvitationRequestDto))
        .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isOk());
  }

  @Test
  public void 초대장이_조회된다() throws Exception {
    List<Template> templateList = templateRepository.findAll();

    Long templateId = templateList.get(0).getId();
    String invitationTitle = "invitationTitle";
    String invitationContents = "invitationContents";
    LocalDateTime invitationTime = LocalDateTime.now();
    String invitationAddressName = "addressName";
    String invitationRoadAddressName = "roadAddressName";
    String invitationPlaceName = "placeName";
    Double latitude = 5.5D;
    Double longitude = 5.6D;
    String hashCode = "hashCode";
    Invitation invitation = Invitation.builder()
        .invitationTitle(invitationTitle)
        .invitationAddressName(invitationAddressName)
        .invitationContents(invitationContents)
        .invitationPlaceName(invitationPlaceName)
        .invitationRoadAddressName(invitationRoadAddressName)
        .invitationTime(invitationTime)
        .x(latitude)
        .y(longitude)
        .templatesId(templateId)
        .hashCode(hashCode)
        .build();
    invitationRepository.save(invitation);

    String getInvitationUrl = "/apis/v2/invitations/" + hashCode;

    MvcResult mvcResult = mvc.perform(get(getInvitationUrl))
        .andExpect(status().isOk())
        .andReturn();

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    ResDetailInvitationDto resDetailInvitationDto = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), ResDetailInvitationDto.class);

    assertThat(resDetailInvitationDto.getInvitationTitle()).isEqualTo(invitationTitle);
    assertThat(resDetailInvitationDto.getInvitationContents()).isEqualTo(invitationContents);
    assertThat(resDetailInvitationDto.getInvitationTime()).isEqualTo(invitationTime);
  }
}

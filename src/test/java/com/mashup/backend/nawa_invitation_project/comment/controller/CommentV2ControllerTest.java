package com.mashup.backend.nawa_invitation_project.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashup.backend.nawa_invitation_project.comment.domain.Comment;
import com.mashup.backend.nawa_invitation_project.comment.domain.CommentRepository;
import com.mashup.backend.nawa_invitation_project.comment.dto.request.PostCommentRequestDto;
import com.mashup.backend.nawa_invitation_project.comment.dto.response.CommentResponseDto;
import com.mashup.backend.nawa_invitation_project.comment.dto.response.GetCommentResponseDto;
import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.invitation.dto.request.PostInvitationRequestDto;
import com.mashup.backend.nawa_invitation_project.invitation.dto.response.HashCodeResponseDto;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentV2ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InvitationRepository invitationRepository;

  @Autowired
  private CommentRepository commentRepository;

  private String invitationHashCode;

  @Before
  public void setUp() throws Exception {
    String userPostUrl = "/apis/v2/users";
    String deviceIdentifierHeaderName = "deviceIdentifier";
    String deviceIdentifier = "abc";

    mvc.perform(post(userPostUrl)
        .header(deviceIdentifierHeaderName, deviceIdentifier))
        .andExpect(status().isOk());

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

    MvcResult mvcResult = mvc.perform(post(invitationPostUrl)
        .header(deviceIdentifierHeaderName, deviceIdentifier)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .content(new ObjectMapper().writeValueAsString(postInvitationRequestDto)))
        .andExpect(status().isOk())
        .andReturn();

    ObjectMapper mapper = new ObjectMapper();
    HashCodeResponseDto hashCodeResponseDto = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashCodeResponseDto.class);
    this.invitationHashCode = hashCodeResponseDto.getInvitationHashCode();
  }

  @After
  public void tearDown() {
    commentRepository.deleteAll();
    invitationRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  public void 초대장에_댓글을_추가한다() throws Exception {
    String postCommentUrl = "/apis/v2/invitations/" + this.invitationHashCode + "/comments";

    String commentContent = "commentContent";
    String commentUserName = "commentUserName";
    PostCommentRequestDto postCommentRequestDto = PostCommentRequestDto.builder()
        .content(commentContent)
        .userName(commentUserName)
        .build();

    mvc.perform(post(postCommentUrl)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(postCommentRequestDto)))
        .andExpect(status().isOk());
  }

  @Test
  public void 초대장의_댓글들을_불러온다() throws Exception {
    Optional<Invitation> invitation = invitationRepository.findByHashCode(this.invitationHashCode);

    String commentContent = "commentContent";
    String userName = "commentUserName";
    commentRepository.save(Comment.builder()
        .content(commentContent)
        .invitationsId(invitation.get().getId())
        .userName(userName)
        .build());

    String getCommentUrl = "/apis/v2/invitations/" + this.invitationHashCode + "/comments";

    MvcResult mvcResult = mvc.perform(get(getCommentUrl))
        .andExpect(status().isOk())
        .andReturn();

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    GetCommentResponseDto getCommentResponseDto = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), GetCommentResponseDto.class);

    List<CommentResponseDto> comments = getCommentResponseDto.getComments();
    assertThat(comments.size()).isGreaterThan(0);
  }
}

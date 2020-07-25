package com.mashup.backend.nawa_invitation_project.template.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.mashup.backend.nawa_invitation_project.invitation.domain.Invitation;
import com.mashup.backend.nawa_invitation_project.invitation.domain.InvitationRepository;
import com.mashup.backend.nawa_invitation_project.template.dto.response.ResInvitationTypeListDto;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import com.mashup.backend.nawa_invitation_project.user.domain.UserRepository;
import java.net.URI;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemplateControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;//JPA 기능도 test 가능

  @Autowired
  private TemplateRepository templateRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InvitationRepository invitationRepository;

  @After
  public void tearDown() {
    templateRepository.deleteAll();
    userRepository.deleteAll();
    invitationRepository.deleteAll();
  }

  @Test
  public void 초대장_템플릿_유형_리스트_받기() throws Exception {
    //given
    templateRepository.save(Template.builder()
        .typeName("애교만점형")
        .typeDescription("난 너가 꼭 와주면 좋겠어 와줄꺼지?")
        .imageUrl("test_image1.jpg")
        .build());

    templateRepository.save(Template.builder()
        .typeName("만수르형")
        .typeDescription("내가 쏠테니까 넌 오기만 해")
        .imageUrl("test_image2.jpg")
        .build());

    User user = userRepository.save(User.builder().deviceIdentifier("123123").build());
    invitationRepository.save(Invitation.builder().usersId(user.getId()).templatesId(1L).build());

    String url = "http://localhost:" + port + "/invitation-types-list?deviceIdentifier=123123";
    URI uri = new URI(url);

    //when
    ResponseEntity<ResInvitationTypeListDto> responseEntity = restTemplate
        .getForEntity(uri, ResInvitationTypeListDto.class);

    //then
    //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    //assertThat(responseEntity.getBody().getInvitationTypeItemList().get(0).getIsExistInvitation(),
    //    is(true));
  }
}

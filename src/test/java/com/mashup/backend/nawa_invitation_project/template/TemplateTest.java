package com.mashup.backend.nawa_invitation_project.template;

import com.mashup.backend.nawa_invitation_project.template.domain.Template;
import com.mashup.backend.nawa_invitation_project.template.domain.TemplateRepository;
import com.mashup.backend.nawa_invitation_project.user.domain.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {

  @Autowired
  private TemplateRepository templateRepository;

  @After
  public void cleanup() {
    templateRepository.deleteAll();
  }

  @Test
  public void 템플릿_생성_테스트() {
    Template createdTemplate = templateRepository.save(Template.builder()
        .typeName("애교만점형")
        .typeDescription("난 너가 꼭 와주면 좋겠어\uD83D\uDC96 와줄꺼지?")
        .imageUrl("test_image.jpg")
        .build());

    assertThat(createdTemplate.getId(), is(1L));
    assertThat(createdTemplate.getTypeName(), is("애교만점형"));
  }

  @Test
  public void Where_어노테이션_없는_템플릿_소프트삭제_테스트(){
    Template savedTemplate = templateRepository.save(Template.builder()
        .typeName("애교만점형")
        .typeDescription("난 너가 꼭 와주면 좋겠어\uD83D\uDC96 와줄꺼지?")
        .imageUrl("test_image.jpg")
        .build());

    assertNull(savedTemplate.getRemovedAt()); //removed_at=null 일때 삭제되지 않은 템플릿

    templateRepository.delete(savedTemplate);
    Template deletedTemplate = templateRepository.findById(1L)
        .orElseThrow(()-> new IllegalArgumentException("no such template"));

    assertThat(deletedTemplate.getRemovedAt().isBefore(LocalDateTime.now()), is(true));
  }

  @Test
  public void Where_어노테이션_있는_템플릿_소프트삭제_테스트(){
    Template savedTemplate = templateRepository.save(Template.builder()
        .typeName("애교만점형")
        .typeDescription("난 너가 꼭 와주면 좋겠어\uD83D\uDC96 와줄꺼지?")
        .imageUrl("test_image.jpg")
        .build());

    templateRepository.delete(savedTemplate);
    Optional<Template> deletedTemplate = templateRepository.findById(1L);

    assertThat(deletedTemplate.isPresent(), is(false));
  }
}

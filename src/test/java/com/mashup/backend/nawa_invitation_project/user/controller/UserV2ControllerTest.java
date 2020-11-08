package com.mashup.backend.nawa_invitation_project.user.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserV2ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void deviceIdentifier가_등록된다() throws Exception {
    mvc.perform(post("/apis/v2/users")
        .header("deviceIdentifier", "abc"))
        .andExpect(status().isOk());
  }

  @Test
  public void deviceIdentifier가_없으면_등록이_안된다() throws Exception {
    mvc.perform(post("/apis/v2/users")
        .header("deviceIdentifier", ""))
        .andExpect(status().is4xxClientError());
  }

}

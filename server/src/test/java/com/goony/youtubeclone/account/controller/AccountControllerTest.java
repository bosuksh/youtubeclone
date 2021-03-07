package com.goony.youtubeclone.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goony.youtubeclone.account.dto.AccountRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("create account")
  public void createAccount() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("example@example.com")
                                     .password("123")
                                     .name("example")
                                     .build();
    //when
    mockMvc.perform(post("/api/account")
      .content(objectMapper.writeValueAsString(requestDto))
      .contentType(MediaType.APPLICATION_JSON))
    //then
    .andDo(print())
    .andExpect(status().isCreated())
    .andExpect(header().exists(HttpHeaders.LOCATION))
    .andExpect(jsonPath("id").exists())
    .andExpect(jsonPath("email").exists())
    .andExpect(jsonPath("name").exists())
    .andExpect(jsonPath("password").doesNotExist());
  }
}
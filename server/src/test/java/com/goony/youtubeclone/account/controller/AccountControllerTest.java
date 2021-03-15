package com.goony.youtubeclone.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goony.youtubeclone.account.domain.Account;
import com.goony.youtubeclone.account.dto.AccountRequestDto;
import com.goony.youtubeclone.account.service.AccountService;
import com.goony.youtubeclone.common.error.exception.UserDuplicatedException;
import org.apache.tomcat.util.file.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  AccountService accountService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("create account")
  @Order(1)
  public void createAccount() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("example@example.com")
                                     .password("123")
                                     .name("example")
                                     .build();
    Account account = Account.builder()
                        .id(1L)
                        .name(requestDto.getName())
                        .password(requestDto.getPassword())
                        .email(requestDto.getEmail())
                        .build();
    given(accountService.createAccount(any())).willReturn(account);
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

  @Test
  @DisplayName("create account with duplicated email")
  @Order(2)
  public void createAccountWithDuplicated() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("example@example.com")
                                     .password("123")
                                     .name("example")
                                     .build();
    given(accountService.createAccount(any())).willThrow(new UserDuplicatedException());
    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Email is Duplicated"));

  }

  @Test
  @DisplayName("create account with no email")
  public void createAccountWithInvalidField() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .password("123")
                                     .name("example")
                                     .build();

    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest());

  }

  @Test
  @DisplayName("create account with empty email")
  public void createAccountWithInvalidEmail() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("")
                                     .password("123")
                                     .name("example")
                                     .build();

    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("create account wrong email form")
  public void createAccountWithInvalidEmailForm() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("aaaaaabb")
                                     .password("123")
                                     .name("example")
                                     .build();

    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("create account with no password")
  public void createAccountWithInvalidPassword() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("example@example.com")
                                     .name("example")
                                     .build();

    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest());

  }

  @Test
  @DisplayName("create account with empty password")
  public void createAccountWithEmptyPassword() throws Exception {
    //given
    AccountRequestDto requestDto = AccountRequestDto.builder()
                                     .email("example@example.com")
                                     .password("")
                                     .name("example")
                                     .build();

    //when
    mockMvc.perform(post("/api/account")
                      .content(objectMapper.writeValueAsString(requestDto))
                      .contentType(MediaType.APPLICATION_JSON))
      //then
      .andDo(print())
      .andExpect(status().isBadRequest());
  }


}
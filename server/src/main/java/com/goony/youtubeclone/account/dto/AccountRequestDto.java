package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountRequestDto {

  @Email
  private String email;

  private String password;

  private String name;

}

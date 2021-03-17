package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Builder
@AllArgsConstructor
public class AuthRequestDto {

  @Email @NotBlank
  private String email;

  @NotBlank
  private String password;
}

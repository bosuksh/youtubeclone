package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountRequestDto {

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

}

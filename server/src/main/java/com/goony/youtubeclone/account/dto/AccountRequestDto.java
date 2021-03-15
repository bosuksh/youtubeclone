package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountRequestDto {


  @NotBlank(message = "Email must not be null")
  @Email
  private String email;

  @NotBlank(message = "Password must not be null")
  private String password;

  @NotBlank
  private String name;

}

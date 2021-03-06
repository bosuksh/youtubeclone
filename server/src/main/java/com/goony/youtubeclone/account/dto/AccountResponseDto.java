package com.goony.youtubeclone.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountResponseDto {

  private Long id;

  private String email;

  private String name;

  private String token;

}
